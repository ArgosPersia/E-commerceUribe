package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Empleado;
import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.E_commerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
