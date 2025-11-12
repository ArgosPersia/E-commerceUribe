package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Empleado;
import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.E_commerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa empleadoMapa;

    public EmpleadoDTO guardarEmpleado(Empleado datosEmpleado) {
        // Validación de salario positivo
        if (datosEmpleado.getSalario() == null || datosEmpleado.getSalario() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El salario debe ser mayor a 0"
            );
        }

        // Guardar empleado
        Empleado empleadoGuardado = this.repositorio.save(datosEmpleado);
        if (empleadoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el empleado en la base de datos"
            );
        }

        // Retornar DTO
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }

    //----- Funciones nuevas -----

    // Buscar todos los empleados
    public List<EmpleadoDTO> buscarTodosLosEmpleados() {
        List<Empleado> listaEmpleados = this.repositorio.findAll();
        return this.empleadoMapa.convertir_lista_a_empleadodto(listaEmpleados);
    }

    // Buscar un empleado por ID
    public EmpleadoDTO buscarEmpleadoPorId(Integer id) {
        Optional<Empleado> empleadoOpcional = this.repositorio.findById(id);
        if (!empleadoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el id " + id
            );
        }
        Empleado empleadoEncontrado = empleadoOpcional.get();
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoEncontrado);
    }

    // Eliminar empleado
    public void eliminarEmpleado(Integer id) {
        Optional<Empleado> empleadoOpcional = this.repositorio.findById(id);
        if (!empleadoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el empleado con el id " + id
            );
        }
        Empleado empleadoEncontrado = empleadoOpcional.get();
        try {
            this.repositorio.delete(empleadoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el empleado, " + error.getMessage()
            );
        }
    }

    // Actualizar datos de un empleado
    public EmpleadoDTO actualizarEmpleado(Integer id, Empleado datosActualizados) {
        Optional<Empleado> empleadoOpcional = this.repositorio.findById(id);
        if (!empleadoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el empleado con el id " + id
            );
        }
        Empleado empleadoEncontrado = empleadoOpcional.get();

        // Actualizar los campos permitidos
        // Cargo
        empleadoEncontrado.setCargoEmpleado(datosActualizados.getCargoEmpleado());
        // Salario
        empleadoEncontrado.setSalario(datosActualizados.getSalario());
        // Sede
        empleadoEncontrado.setSedeEmpleado(datosActualizados.getSedeEmpleado());

        // Guardar en la base de datos
        Empleado empleadoActualizado = this.repositorio.save(empleadoEncontrado);
        if (empleadoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el empleado en la base de datos"
            );
        }
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoActualizado);
    }

    // Buscar empleados por sede
    public List<EmpleadoDTO> buscarEmpleadosPorSede(String sede) {
        List<Empleado> todosLosEmpleados = this.repositorio.findAll();
        List<Empleado> empleadosPorSede = new ArrayList<>();

        for (Empleado empleado : todosLosEmpleados) {
            if (empleado.getSedeEmpleado().toString().equals(sede)) {
                empleadosPorSede.add(empleado);
            }
        }

        if (empleadosPorSede.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron empleados en la sede " + sede
            );
        }
        return this.empleadoMapa.convertir_lista_a_empleadodto(empleadosPorSede);
    }
}