package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.ayudas.SedeEmpleado;
import com.example.E_commerceUribe.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer>{
    List<Empleado> findBySedeEmpleado (SedeEmpleado sedeEmpleado);
}

