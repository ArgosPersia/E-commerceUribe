package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation; // <-- ¡Importado!
import io.swagger.v3.oas.annotations.tags.Tag; // <-- ¡Importado!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operaciones tabla empleados")
public class EmpleadoControlador {

    @Autowired
    EmpleadoServicio servicio;

    // Crear Empleado
    @Operation(summary = "Registrar un nuevo empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody EmpleadoDTO datosDTO) {
        EmpleadoDTO empleadoNuevo = this.servicio.guardarEmpleado(datosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoNuevo);
    }

    // Modificar Empleado
    @Operation(summary = "Actualizar la información de un empleado existente por ID")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> modificar(@RequestBody EmpleadoDTO datosDTO, @PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datosDTO);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar todos los Empleados
    @Operation(summary = "Listar todos los empleados registrados")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> lista = this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    // Buscar Por id
    @Operation(summary = "Buscar un empleado por su ID único")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> buscarPorId(@PathVariable Integer id) {
        EmpleadoDTO empleado = this.servicio.buscarEmpleadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(empleado);
    }

    // Eliminar
    @Operation(summary = "Eliminar un empleado de la BD por ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarEmpleado(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}