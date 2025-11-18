package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.DTO.UsuarioEspecialDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapa {

    // --- Mapeo de Entidad a DTO (Para lectura y retorno) ---
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    UsuarioDTO convertir_usuario_a_usuariodto(Usuario usuario);

    // --- Mapeo de DTO a Entidad (CRUCIAL PARA GUARDAR) ---
    // MapStruct infiere la mayoría de los campos si los nombres coinciden.
    // Asegúrate de que los campos en UsuarioDTO y Usuario (incluida la contraseña y tipo de documento)
    // estén mapeados correctamente si tienen nombres diferentes.
    Usuario convertir_usuario_dto_a_usuario(UsuarioDTO usuarioDTO);


    // --- Otros Mapeos ---
    List<UsuarioDTO> convetir_lista_a_listadto(List<Usuario> lista);

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    @Mapping(source = "contraseña", target = "contraseña")
    UsuarioEspecialDTO convertir_usuario_a_usuarioespecialdto(Usuario usuario);
}