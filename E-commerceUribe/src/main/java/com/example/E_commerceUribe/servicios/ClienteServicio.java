package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.mapas.IClienteMapa;
import com.example.E_commerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa clienteMapa;

    public ClienteDTO guardarCliente(Cliente datosCliente) {
        // Validación de dirección obligatoria
        if (datosCliente.getDireccion() == null || datosCliente.getDireccion().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La dirección es obligatoria"
            );
        }

        // Guardar cliente
        Cliente clienteGuardado = this.repositorio.save(datosCliente);
        if (clienteGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el cliente en la base de datos"
            );
        }

        // Retornar DTO
        return this.clienteMapa.convertir_cliente_a_clientedto(clienteGuardado);
    }

    //----- Funciones nuevas -----

    // Buscar todos los clientes
    public List<ClienteDTO> buscarTodosLosClientes() {
        List<Cliente> listaClientes = this.repositorio.findAll();
        return this.clienteMapa.convertir_lista_a_clientedto(listaClientes);
    }

    // Buscar un cliente por ID
    public ClienteDTO buscarClientePorId(Integer id) {
        Optional<Cliente> clienteOpcional = this.repositorio.findById(id);
        if (!clienteOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún cliente con el id " + id
            );
        }
        Cliente clienteEncontrado = clienteOpcional.get();
        return this.clienteMapa.convertir_cliente_a_clientedto(clienteEncontrado);
    }

    // Eliminar cliente
    public void eliminarCliente(Integer id) {
        Optional<Cliente> clienteOpcional = this.repositorio.findById(id);
        if (!clienteOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el cliente con el id " + id
            );
        }
        Cliente clienteEncontrado = clienteOpcional.get();
        try {
            this.repositorio.delete(clienteEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el cliente, " + error.getMessage()
            );
        }
    }

    // Actualizar datos de un cliente
    public ClienteDTO actualizarCliente(Integer id, Cliente datosActualizados) {
        Optional<Cliente> clienteOpcional = this.repositorio.findById(id);
        if (!clienteOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el cliente con el id " + id
            );
        }
        Cliente clienteEncontrado = clienteOpcional.get();

        // Actualizar los campos permitidos
        // Dirección
        clienteEncontrado.setDireccion(datosActualizados.getDireccion());
        // Calificación
        clienteEncontrado.setCalificacion(datosActualizados.getCalificacion());
        // Ciudad
        clienteEncontrado.setCiudad(datosActualizados.getCiudad());

        // Guardar en la base de datos
        Cliente clienteActualizado = this.repositorio.save(clienteEncontrado);
        if (clienteActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente en la base de datos"
            );
        }
        return this.clienteMapa.convertir_cliente_a_clientedto(clienteActualizado);
    }

    // Buscar clientes por departamento
    public List<ClienteDTO> buscarClientesPorDepartamento(String departamento) {
        List<Cliente> todosLosClientes = this.repositorio.findAll();
        List<Cliente> clientesPorDepartamento = new ArrayList<>();

        for (Cliente cliente : todosLosClientes) {
            if (cliente.getDepartamentoCliente().toString().equals(departamento)) {
                clientesPorDepartamento.add(cliente);
            }
        }

        if (clientesPorDepartamento.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron clientes en el departamento " + departamento
            );
        }
        return this.clienteMapa.convertir_lista_a_clientedto(clientesPorDepartamento);
    }
}