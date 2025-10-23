package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.modelos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface IPedidoRepositorio extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByFechaCreacion (LocalDate fechaCreacion);
}

