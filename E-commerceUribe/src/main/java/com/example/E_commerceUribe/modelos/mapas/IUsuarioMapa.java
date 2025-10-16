package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.DTO.UsuarioEspecialDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapa {
    //Se han de crear 2 funciones por mapa (se hacen 2, pero pueden ser más)

    // Primer funcion que transforme 1 modelo a 1 DTO
    @Mapping( source = "nombres", target = "nombres")
    @Mapping( source = "correo", target = "correo")
    @Mapping( source = "estado", target = "estado")
    @Mapping( source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping( source = "documento", target = "documento")
    UsuarioDTO convertir_usuario_a_usuariodto(Usuario usuario);

    // Segunda funcion que transforme una list<modelo> en una list<dto>
    List<UsuarioEspecialDTO> convetir_lista_a_listadto(List<Usuario> lista);

    //------

    // Primer funcion que transforme 1 modelo a 1 DTO
    @Mapping( source = "nombres", target = "nombres")
    @Mapping( source = "correo", target = "correo")
    @Mapping( source = "estado", target = "estado")
    @Mapping( source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping( source = "documento", target = "documento")
    @Mapping( source = "contraseña", target = "contraseña")
    UsuarioEspecialDTO convertir_usuario_a_usuarioespecialdto(Usuario usuario);

    // Segunda funcion que transforme una list<modelo> en una list<dto>
    List<UsuarioEspecialDTO> convetir_lista_a_listaespecialdto(List<Usuario> lista);
}
