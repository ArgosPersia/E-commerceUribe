package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.ProductoDTO;
import com.example.E_commerceUribe.modelos.Producto;
import com.example.E_commerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Controlador para operacion tabla productos")
public class ProductoControlador {

    @Autowired
    ProductoServicio servicio;

    //Crear un producto
    @Operation(summary = "Crear un producto en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoDTO> guardar(@RequestBody Producto datos) {
        ProductoDTO respuesta = this.servicio.guardarProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //Lista de los productos
    @Operation(summary = "Listar todos los productos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> respuesta = this.servicio.buscarTodosLosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Buscar un producto
    @Operation(summary = "Buscar un producto en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Integer id) {
        ProductoDTO respuesta = this.servicio.buscarProductoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Elimina un producto
    @Operation(summary = "Elimina un producto de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    //Modifica un producto
    @Operation(summary = "Modifica un producto en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> modificar(@RequestBody Producto datos, @PathVariable Integer id) {
        ProductoDTO respuesta = this.servicio.actualizarProducto(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Busca un producto por marca
    @Operation(summary = "Buscar productos por marca")
    @GetMapping(value = "/marca/{marca}", produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> buscarPorMarca(@PathVariable String marca) {
        List<ProductoDTO> respuesta = this.servicio.buscarProductosPorMarca(marca);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}