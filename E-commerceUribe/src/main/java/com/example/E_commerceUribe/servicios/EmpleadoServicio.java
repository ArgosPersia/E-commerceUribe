package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Empleado;
import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.E_commerceUribe.repositorios.IEmpleadoRepositorio;
// <<<< IMPORTACIONES AÑADIDAS >>>>
import com.example.E_commerceUribe.repositorios.IUsuarioRepositorio;
import com.example.E_commerceUribe.ayudas.SedeEmpleado;
// <<<< -------------------- >>>>
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


    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;


    // 1. MÉTODO GUARDAR EMPLEADO (POST)
    public EmpleadoDTO guardarEmpleado(EmpleadoDTO datosDTO) {

        // 1. Convertir DTO a Entidad
        Empleado empleado = this.empleadoMapa.convertir_empleado_dto_a_empleado(datosDTO);

        // Validación de salario positivo
        if (empleado.getSalario() == null || empleado.getSalario() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El salario debe ser mayor a 0"
            );
        }

        // Validación de Usuario existente (Crucial para el FK)
        if (empleado.getUsuario() == null || empleado.getUsuario().getId() == null ||
                !usuarioRepositorio.existsById(empleado.getUsuario().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El ID de usuario proporcionado para el empleado no existe."
            );
        }

        // Guardar empleado
        Empleado empleadoGuardado = this.repositorio.save(empleado);
        if (empleadoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el empleado en la base de datos"
            );
        }

        // Retornar DTO
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }

    //----- Funciones -----

    // Buscar todos los empleados
    public List<EmpleadoDTO> buscarTodosLosEmpleados() {
        List<Empleado> listaEmpleados = this.repositorio.findAll();
        return this.empleadoMapa.convertir_lista_a_empleadodto(listaEmpleados);
    }

    // Buscar un empleado por ID
    public EmpleadoDTO buscarEmpleadoPorId(Integer id) {
        Empleado empleadoEncontrado = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró ningún empleado con el id " + id)
                );
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoEncontrado);
    }

    // Eliminar empleado
    public void eliminarEmpleado(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el empleado con el id " + id
            );
        }
        try {
            this.repositorio.deleteById(id); // Cambiado a deleteById para mayor eficiencia
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el empleado, " + error.getMessage()
            );
        }
    }

    // 2. MÉTODO ACTUALIZAR EMPLEADO (PUT)

    public EmpleadoDTO actualizarEmpleado(Integer id, EmpleadoDTO datosDTO) {
        Empleado empleadoEncontrado = this.repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró el empleado con el id " + id)
                );

        // 1. Convertir el DTO para obtener la entidad con los nuevos datos
        Empleado datosActualizados = this.empleadoMapa.convertir_empleado_dto_a_empleado(datosDTO);

        // Actualizar los campos permitidos
        empleadoEncontrado.setCargoEmpleado(datosActualizados.getCargoEmpleado());
        empleadoEncontrado.setSalario(datosActualizados.getSalario());
        empleadoEncontrado.setSedeEmpleado(datosActualizados.getSedeEmpleado());

        // Guardar en la base de datos
        Empleado empleadoActualizado = this.repositorio.save(empleadoEncontrado);

        // Retornar DTO
        return this.empleadoMapa.convertir_empleado_a_empleadodto(empleadoActualizado);
    }

    // 3. MÉTODO BUSCAR POR SEDE
    public List<EmpleadoDTO> buscarEmpleadosPorSede(String sede) {

        SedeEmpleado sedeEnum;
        try {
            sedeEnum = SedeEmpleado.valueOf(sede.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El valor de sede '" + sede + "' no es válido."
            );
        }

        // Usar el método del repositorio que ya existe
        List<Empleado> empleadosPorSede = this.repositorio.findBySedeEmpleado(sedeEnum);

        if (empleadosPorSede.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron empleados en la sede " + sede
            );
        }
        return this.empleadoMapa.convertir_lista_a_empleadodto(empleadosPorSede);
    }
}