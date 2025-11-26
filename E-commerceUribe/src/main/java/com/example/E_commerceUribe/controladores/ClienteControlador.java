package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
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

    //Crear un cliente
    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody ClienteDTO datosDTO) {
        ClienteDTO respuesta = this.servicio.guardarCliente(datosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //Lista de todos los clientes
    @Operation(summary = "Listar todos los clientes guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> respuesta = this.servicio.buscarTodosLosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Buscar un cliente por id
    @Operation(summary = "Buscar un cliente en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id) {
        ClienteDTO respuesta = this.servicio.buscarClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Eliminar
    @Operation(summary = "Elimina un cliente de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    //Modifcar un cliente
    @Operation(summary = "Modifica un cliente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> modificar(@RequestBody ClienteDTO datosDTO, @PathVariable Integer id) {
        ClienteDTO respuesta = this.servicio.actualizarCliente(id, datosDTO);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Buscar cliente por departamento
    @Operation(summary = "Buscar clientes por departamento")
    @GetMapping(value = "/departamento/{departamento}", produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> buscarPorDepartamento(@PathVariable String departamento) {
        List<ClienteDTO> respuesta = this.servicio.buscarClientesPorDepartamento(departamento);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}