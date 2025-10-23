package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.mapas.IClienteMapa;
import com.example.E_commerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
