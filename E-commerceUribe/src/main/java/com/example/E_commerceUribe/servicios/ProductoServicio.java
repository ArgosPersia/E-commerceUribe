package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Producto;
import com.example.E_commerceUribe.modelos.DTO.ProductoDTO;
import com.example.E_commerceUribe.modelos.mapas.IProductoMapa;
import com.example.E_commerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa productoMapa;

    public ProductoDTO guardarProducto(Producto datosProducto) {
        // Validación de nombre obligatorio
        if (datosProducto.getNombre() == null || datosProducto.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del producto es obligatorio"
            );
        }

        // Guardar producto
        Producto productoGuardado = this.repositorio.save(datosProducto);
        if (productoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el producto en la base de datos"
            );
        }

        // Retornar DTO
        return this.productoMapa.convertir_producto_a_productodto(productoGuardado);
    }
}
