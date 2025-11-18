package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    // 1. CREAR EMPLEADO (POST) - ¡Añadido!
    @PostMapping
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody EmpleadoDTO datosDTO) {
        EmpleadoDTO empleadoNuevo = this.servicio.guardarEmpleado(datosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoNuevo);
    }

    // 2. MODIFICAR EMPLEADO (PUT) - Ya existía, se mantiene.
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoDTO> modificar(@RequestBody EmpleadoDTO datosDTO, @PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datosDTO);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // 3. BUSCAR TODOS (GET) - Esencial para la funcionalidad
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> lista = this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    // 4. ELIMINAR (DELETE) - Esencial para la prueba CRUD
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarEmpleado(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}