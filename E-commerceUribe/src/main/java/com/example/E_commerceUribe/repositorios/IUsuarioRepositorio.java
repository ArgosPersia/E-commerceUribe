package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombres(String nombres);
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByFechaNacimiento(LocalDate fechaNacimiento);
}
