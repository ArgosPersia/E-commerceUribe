package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.mapas.IClienteMapa;
import com.example.E_commerceUribe.repositorios.IClienteRepositorio;
import com.example.E_commerceUribe.repositorios.IUsuarioRepositorio;
import com.example.E_commerceUribe.ayudas.DepartamentoCliente; // Importar el Enum
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa clienteMapa;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    // 1. MÉTODO GUARDAR
    public ClienteDTO guardarCliente(ClienteDTO datosDTO) {

        Cliente cliente = this.clienteMapa.convertir_cliente_dto_a_cliente(datosDTO);

        // Validación: El usuario debe existir
        if (cliente.getUsuario() == null || cliente.getUsuario().getId() == null ||
                !usuarioRepositorio.existsById(cliente.getUsuario().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El ID de usuario proporcionado no existe."
            );
        }

        Cliente clienteGuardado = this.repositorio.save(cliente);
        return this.clienteMapa.convertir_cliente_a_clientedto(clienteGuardado);
    }

    // 2. MÉTODO LISTAR
    public List<ClienteDTO> buscarTodosLosClientes() {
        List<Cliente> clientes = this.repositorio.findAll();
        return this.clienteMapa.convertir_lista_a_listadto(clientes);
    }

    // 3. MÉTODO BUSCAR POR ID
    public ClienteDTO buscarClientePorId(Integer id) {
        Cliente cliente = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));
        return this.clienteMapa.convertir_cliente_a_clientedto(cliente);
    }

    // 4. MÉTODO ELIMINAR
    public void eliminarCliente(Integer id){
        if (!this.repositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se ha encontrado el cliente con el ID "+id+" suministrado"
            );
        }
        try {
            this.repositorio.deleteById(id);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el cliente: "+error.getMessage()
            );
        }
    }

    // 5. MÉTODO ACTUALIZAR
    public ClienteDTO actualizarCliente(Integer id, ClienteDTO datosDTO) {
        Cliente clienteEncontrado = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se ha encontrado el cliente con el ID " + id + " suministrado")
                );

        // Mapear los campos del DTO al cliente existente
        Cliente datosActualizados = this.clienteMapa.convertir_cliente_dto_a_cliente(datosDTO);

        // Solo actualizamos los campos modificables
        clienteEncontrado.setDireccion(datosActualizados.getDireccion());
        clienteEncontrado.setCalificacion(datosActualizados.getCalificacion());
        clienteEncontrado.setReferenciaPago(datosActualizados.getReferenciaPago());
        clienteEncontrado.setDepartamentoCliente(datosActualizados.getDepartamentoCliente());
        clienteEncontrado.setCiudad(datosActualizados.getCiudad());

        Cliente clienteActualizado = this.repositorio.save(clienteEncontrado);

        return this.clienteMapa.convertir_cliente_a_clientedto(clienteActualizado);
    }

    // 6. MÉTODO BUSCAR POR DEPARTAMENTO (CORRECCIÓN FINAL DE TIPOS)
    public List<ClienteDTO> buscarClientesPorDepartamento(String departamento) {

        DepartamentoCliente departamentoEnum;
        try {
            // CONVERSIÓN CRUCIAL: Convierte el String de entrada a MAYÚSCULAS y luego al Enum
            departamentoEnum = DepartamentoCliente.valueOf(departamento.toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El valor de departamento '" + departamento + "' no es válido."
            );
        }

        // Usa el Enum en el repositorio (asumiendo que IClienteRepositorio tiene findByDepartamentoCliente(Enum))
        List<Cliente> clientes = this.repositorio.findByDepartamentoCliente(departamentoEnum);

        if (clientes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron clientes en el departamento: " + departamento
            );
        }

        return this.clienteMapa.convertir_lista_a_listadto(clientes);
    }
}