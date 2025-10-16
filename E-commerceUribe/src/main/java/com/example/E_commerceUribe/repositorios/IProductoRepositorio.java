package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {
    List<Producto> findByPrecioUnitario (Integer precioUnitario);
}

