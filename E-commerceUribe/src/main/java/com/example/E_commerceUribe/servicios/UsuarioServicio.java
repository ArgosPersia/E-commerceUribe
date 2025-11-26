package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import com.example.E_commerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.E_commerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IUsuarioMapa usuarioMapa;

    // MÉTODO GUARDAR CORREGIDO: Acepta DTO, convierte a Entidad para guardar.
    public UsuarioDTO guardarUsuario(UsuarioDTO datosDTO) {

        // CONVERSIÓN DTO a Entidad (Usuario)
        Usuario datosUsuario = this.usuarioMapa.convertir_usuario_dto_a_usuario(datosDTO);

        // Validación: correo duplicado
        if (this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT ,
                    "Ya existe un usuario con ese correo"
            );
        }

        // Validación: nombre obligatorio
        if (datosUsuario.getNombres() == null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del usuario es obligatorio"
            );
        }

        // Validación: longitud mínima de contraseña
        if (datosUsuario.getContraseña() == null || datosUsuario.getContraseña().length() < 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña debe tener al menos 6 caracteres"
            );
        }

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = this.repositorio.save(datosUsuario);

        if (usuarioGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el usuario en la base de datos"
            );
        }

        // Retornar el DTO del usuario guardado
        return this.usuarioMapa.convertir_usuario_a_usuariodto(usuarioGuardado);
    }

    //----- Funciones-----

    public List<UsuarioDTO> buscarListaDeUsuarios(){
        List<Usuario> listaDeUsuarios = this.repositorio.findAll();
        return this.usuarioMapa.convetir_lista_a_listadto(listaDeUsuarios);
    }

    // Buscar un usuario por ID
    public UsuarioDTO BuscarUsuarioPorId(Integer id){
        Optional<Usuario> usuarioOpcional = this.repositorio.findById(id);
        if (!usuarioOpcional.isPresent()) {
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id "+id+" "
            );
        }
        Usuario usuarioEncontrado = usuarioOpcional.get();
        return this.usuarioMapa.convertir_usuario_a_usuariodto(usuarioEncontrado);
    }

    // Eliminar usuario
    public void eliminarUsuario(Integer id){
        Optional<Usuario> usuarioOpcional = this.repositorio.findById(id);
        if (!usuarioOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Se ha encontrado el usuario con el id "+id+" suministrado"
            );
        }
        Usuario usuarioEncontrado = usuarioOpcional.get();
        try {
            this.repositorio.delete(usuarioEncontrado);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el usuario con el id, "+error.getMessage()
            );
        }
    }
    // Modificador algunos datos de un usuario
    // NOTA: Para ser consistente, este método también debería recibir UsuarioDTO
    public UsuarioDTO actualizarUsuario(Integer id,  Usuario datosActualizados) {
        Optional<Usuario> usuarioOpcional = this.repositorio.findById(id);
        if (!usuarioOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Se ha encontrado el usuario con el id " + id + " suministrado"
            );
        }
        Usuario usuarioEncontrado = usuarioOpcional.get();
        //Aplicar validación sobre los datos que se resiven desde el front

        //Actualizar los campos que se han permitido modificar
        //Nombre
        usuarioEncontrado.setNombres(datosActualizados.getNombres());
        //Correo
        usuarioEncontrado.setCorreo(datosActualizados.getCorreo());

        //Concluir con la operación en la base de datos
        Usuario usuarioActualizo = this.repositorio.save(usuarioEncontrado);
        if (usuarioActualizo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el usuario en la base de datos, intente nuevamente"
            );
        }
        return this.usuarioMapa.convertir_usuario_a_usuariodto(usuarioActualizo);
    }
}