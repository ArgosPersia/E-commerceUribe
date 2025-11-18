package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {

    // 1. DTO a Entidad: Usamos usuarioId del DTO para llenar usuario.id de la Entidad.
    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(target = "id", ignore = true) // Ignoramos el ID en la creación
    Empleado convertir_empleado_dto_a_empleado(EmpleadoDTO empleadoDTO);

    // 2. Entidad a DTO: Usamos usuario.id de la Entidad para llenar usuarioId del DTO.
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "cargoEmpleado", target = "cargoEmpleado")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "sedeEmpleado", target = "sedeEmpleado")
    EmpleadoDTO convertir_empleado_a_empleadodto(Empleado empleado);

    // 3. Mapeo de listas
    List<EmpleadoDTO> convertir_lista_a_empleadodto(List<Empleado> lista);
}