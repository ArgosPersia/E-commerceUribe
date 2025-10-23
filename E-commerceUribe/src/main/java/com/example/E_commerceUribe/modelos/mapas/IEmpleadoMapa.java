package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "cargoEmpleado", target = "cargoEmpleado")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "sedeEmpleado", target = "sedeEmpleado")
    EmpleadoDTO convertir_empleado_a_empleadodto(Empleado empleado);

    List<EmpleadoDTO> convertir_lista_a_empleadodto(List<Empleado> lista);
}