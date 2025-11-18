package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.DTO.UsuarioEspecialDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T22:51:11-0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class IUsuarioMapaImpl implements IUsuarioMapa {

    @Override
    public UsuarioDTO convertir_usuario_a_usuariodto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNombres( usuario.getNombres() );
        usuarioDTO.setCorreo( usuario.getCorreo() );
        usuarioDTO.setEstado( usuario.getEstado() );
        usuarioDTO.setFechaNacimiento( usuario.getFechaNacimiento() );
        usuarioDTO.setDocumento( usuario.getDocumento() );
        usuarioDTO.setContraseña( usuario.getContraseña() );
        usuarioDTO.setTipoDocumento( usuario.getTipoDocumento() );

        return usuarioDTO;
    }

    @Override
    public Usuario convertir_usuario_dto_a_usuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombres( usuarioDTO.getNombres() );
        usuario.setCorreo( usuarioDTO.getCorreo() );
        usuario.setContraseña( usuarioDTO.getContraseña() );
        usuario.setEstado( usuarioDTO.getEstado() );
        usuario.setFechaNacimiento( usuarioDTO.getFechaNacimiento() );
        usuario.setTipoDocumento( usuarioDTO.getTipoDocumento() );
        usuario.setDocumento( usuarioDTO.getDocumento() );

        return usuario;
    }

    @Override
    public List<UsuarioDTO> convetir_lista_a_listadto(List<Usuario> lista) {
        if ( lista == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( lista.size() );
        for ( Usuario usuario : lista ) {
            list.add( convertir_usuario_a_usuariodto( usuario ) );
        }

        return list;
    }

    @Override
    public UsuarioEspecialDTO convertir_usuario_a_usuarioespecialdto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioEspecialDTO usuarioEspecialDTO = new UsuarioEspecialDTO();

        usuarioEspecialDTO.setNombres( usuario.getNombres() );
        usuarioEspecialDTO.setCorreo( usuario.getCorreo() );
        usuarioEspecialDTO.setEstado( usuario.getEstado() );
        usuarioEspecialDTO.setFechaNacimiento( usuario.getFechaNacimiento() );
        usuarioEspecialDTO.setDocumento( usuario.getDocumento() );
        usuarioEspecialDTO.setContraseña( usuario.getContraseña() );

        return usuarioEspecialDTO;
    }
}
