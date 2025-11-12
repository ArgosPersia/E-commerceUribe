package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.Empleado;
import com.example.E_commerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operacion tabla empleados")
public class EmpleadoControlador {

    @Autowired
    EmpleadoServicio servicio;

    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody Empleado datos) {
        EmpleadoDTO respuesta = this.servicio.guardarEmpleado(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los empleados guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> respuesta = this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un empleado en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> buscarPorId(@PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.buscarEmpleadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Elimina un empleado de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modifica un empleado en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> modificar(@RequestBody Empleado datos, @PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar empleados por sede")
    @GetMapping(value = "/sede/{sede}", produces = "application/json")
    public ResponseEntity<List<EmpleadoDTO>> buscarPorSede(@PathVariable String sede) {
        List<EmpleadoDTO> respuesta = this.servicio.buscarEmpleadosPorSede(sede);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}