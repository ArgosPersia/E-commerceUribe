package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Producto;
import com.example.E_commerceUribe.modelos.DTO.ProductoDTO;
import com.example.E_commerceUribe.modelos.mapas.IProductoMapa;
import com.example.E_commerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //----- Funciones-----

    // Buscar todos los productos
    public List<ProductoDTO> buscarTodosLosProductos() {
        List<Producto> listaProductos = this.repositorio.findAll();
        return this.productoMapa.convertir_lista_a_productodto(listaProductos);
    }

    // Buscar un producto por ID
    public ProductoDTO buscarProductoPorId(Integer id) {
        Optional<Producto> productoOpcional = this.repositorio.findById(id);
        if (!productoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el id " + id
            );
        }
        Producto productoEncontrado = productoOpcional.get();
        return this.productoMapa.convertir_producto_a_productodto(productoEncontrado);
    }

    // Eliminar producto
    public void eliminarProducto(Integer id) {
        Optional<Producto> productoOpcional = this.repositorio.findById(id);
        if (!productoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el producto con el id " + id
            );
        }
        Producto productoEncontrado = productoOpcional.get();
        try {
            this.repositorio.delete(productoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el producto, " + error.getMessage()
            );
        }
    }

    // Actualizar datos de un producto
    public ProductoDTO actualizarProducto(Integer id, Producto datosActualizados) {
        Optional<Producto> productoOpcional = this.repositorio.findById(id);
        if (!productoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el producto con el id " + id
            );
        }
        Producto productoEncontrado = productoOpcional.get();

        // Actualizar los campos permitidos
        // Nombre
        productoEncontrado.setNombre(datosActualizados.getNombre());
        // Precio
        productoEncontrado.setPrecioUnitario(datosActualizados.getPrecioUnitario());
        // Descripción
        productoEncontrado.setDescripcion(datosActualizados.getDescripcion());

        // en la entidad existente, evitando la duplicación.
        productoEncontrado.setPedido(datosActualizados.getPedido());

        // Guardar en la base de datos
        Producto productoActualizado = this.repositorio.save(productoEncontrado);
        if (productoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el producto en la base de datos"
            );
        }
        return this.productoMapa.convertir_producto_a_productodto(productoActualizado);
    }

    // Buscar productos por marca
    public List<ProductoDTO> buscarProductosPorMarca(String marca) {
        List<Producto> todosLosProductos = this.repositorio.findAll();
        List<Producto> productosPorMarca = new ArrayList<>();

        for (Producto producto : todosLosProductos) {
            if (producto.getMarca().equals(marca)) {
                productosPorMarca.add(producto);
            }
        }

        if (productosPorMarca.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos de la marca " + marca
            );
        }
        return this.productoMapa.convertir_lista_a_productodto(productosPorMarca);
    }
}