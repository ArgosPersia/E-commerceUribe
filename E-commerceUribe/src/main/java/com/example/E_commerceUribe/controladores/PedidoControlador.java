package com.example.E_commerceUribe.controladores;

import com.example.E_commerceUribe.modelos.DTO.PedidoDTO;
import com.example.E_commerceUribe.modelos.Pedido;
import com.example.E_commerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Controlador para operacion tabla pedidos")
public class PedidoControlador {

    @Autowired
    PedidoServicio servicio;

    //Crear un pedido
    @Operation(summary = "Crear un pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDTO> guardar(@RequestBody Pedido datos) {
        PedidoDTO respuesta = this.servicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //Lista de los pedidos
    @Operation(summary = "Listar todos los pedidos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoDTO>> listar() {
        List<PedidoDTO> respuesta = this.servicio.buscarTodosLosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Busca un pedido por id
    @Operation(summary = "Buscar un pedido en la BD")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
        PedidoDTO respuesta = this.servicio.buscarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Elimina un pedido
    @Operation(summary = "Elimina un pedido de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    //Modifica un pedido
    @Operation(summary = "Modifica un pedido en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoDTO> modificar(@RequestBody Pedido datos, @PathVariable Integer id) {
        PedidoDTO respuesta = this.servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //Buscar un pedido por fecha
    @Operation(summary = "Buscar pedidos por fecha")
    @GetMapping(value = "/fecha/{fecha}", produces = "application/json")
    public ResponseEntity<List<PedidoDTO>> buscarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<PedidoDTO> respuesta = this.servicio.buscarPedidosPorFecha(fecha);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}