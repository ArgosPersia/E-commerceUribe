package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.EmpleadoDTO;
import com.example.E_commerceUribe.modelos.Empleado;
import com.example.E_commerceUribe.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T08:00:32-0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class IEmpleadoMapaImpl implements IEmpleadoMapa {

    @Override
    public Empleado convertir_empleado_dto_a_empleado(EmpleadoDTO empleadoDTO) {
        if ( empleadoDTO == null ) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setUsuario( empleadoDTOToUsuario( empleadoDTO ) );
        empleado.setCargoEmpleado( empleadoDTO.getCargoEmpleado() );
        empleado.setSalario( empleadoDTO.getSalario() );
        empleado.setSedeEmpleado( empleadoDTO.getSedeEmpleado() );

        return empleado;
    }

    @Override
    public EmpleadoDTO convertir_empleado_a_empleadodto(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setUsuarioId( empleadoUsuarioId( empleado ) );
        empleadoDTO.setCargoEmpleado( empleado.getCargoEmpleado() );
        empleadoDTO.setSalario( empleado.getSalario() );
        empleadoDTO.setSedeEmpleado( empleado.getSedeEmpleado() );
        empleadoDTO.setId( empleado.getId() );

        return empleadoDTO;
    }

    @Override
    public List<EmpleadoDTO> convertir_lista_a_empleadodto(List<Empleado> lista) {
        if ( lista == null ) {
            return null;
        }

        List<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>( lista.size() );
        for ( Empleado empleado : lista ) {
            list.add( convertir_empleado_a_empleadodto( empleado ) );
        }

        return list;
    }

    protected Usuario empleadoDTOToUsuario(EmpleadoDTO empleadoDTO) {
        if ( empleadoDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( empleadoDTO.getUsuarioId() );

        return usuario;
    }

    private Integer empleadoUsuarioId(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }
        Usuario usuario = empleado.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Integer id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
