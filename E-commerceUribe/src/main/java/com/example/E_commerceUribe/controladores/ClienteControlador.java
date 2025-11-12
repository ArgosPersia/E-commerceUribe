package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Controlador para operacion tabla clientes")
public class ClienteControlador {

    @Autowired
    ClienteServicio servicio;

    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody Cliente datos) {
        ClienteDTO respuesta = this.servicio.guardarCliente(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los clientes guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> respuesta = this.servicio.buscarTodosLosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un cliente en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
        ClienteDTO respuesta = this.servicio.buscarClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Elimina un cliente de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modifica un cliente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> modificar(@RequestBody Cliente datos, @PathVariable Integer id) {
        ClienteDTO respuesta = this.servicio.actualizarCliente(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar clientes por departamento")
    @GetMapping(value = "/departamento/{departamento}", produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> buscarPorDepartamento(@PathVariable String departamento) {
        List<ClienteDTO> respuesta = this.servicio.buscarClientesPorDepartamento(departamento);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}