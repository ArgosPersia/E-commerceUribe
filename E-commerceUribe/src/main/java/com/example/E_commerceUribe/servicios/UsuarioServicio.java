package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.DTO.UsuarioDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import com.example.E_commerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.E_commerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IUsuarioMapa usuarioMapa;

    public UsuarioDTO guardarUsuario(Usuario datosUsuario) {

        // Validación: correo duplicado
        if (this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
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
        if (datosUsuario.getContraseña().length() < 6) {
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
}
