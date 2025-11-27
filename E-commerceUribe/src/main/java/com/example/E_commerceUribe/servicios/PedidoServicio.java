package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Pedido;
import com.example.E_commerceUribe.modelos.DTO.PedidoDTO;
import com.example.E_commerceUribe.modelos.mapas.IPedidoMapa;
import com.example.E_commerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa pedidoMapa;

    public PedidoDTO guardarPedido(Pedido datosPedido) {
        // Validación de monto positivo
        if (datosPedido.getMontoTotal() == null || datosPedido.getMontoTotal() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El monto total debe ser positivo"
            );
        }

        // Guardar pedido
        Pedido pedidoGuardado = this.repositorio.save(datosPedido);
        if (pedidoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido en la base de datos"
            );
        }

        // Retornar DTO
        return this.pedidoMapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }

    //----- Funciones -----

    // Buscar todos los pedidos
    public List<PedidoDTO> buscarTodosLosPedidos() {
        List<Pedido> listaPedidos = this.repositorio.findAll();
        return this.pedidoMapa.convertir_lista_a_pedidodto(listaPedidos);
    }

    // Buscar un pedido por ID
    public PedidoDTO buscarPedidoPorId(Integer id) {
        Optional<Pedido> pedidoOpcional = this.repositorio.findById(id);
        if (!pedidoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el id " + id
            );
        }
        Pedido pedidoEncontrado = pedidoOpcional.get();
        return this.pedidoMapa.convertir_pedido_a_pedidodto(pedidoEncontrado);
    }

    // Eliminar pedido
    public void eliminarPedido(Integer id) {
        Optional<Pedido> pedidoOpcional = this.repositorio.findById(id);
        if (!pedidoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el pedido con el id " + id
            );
        }
        Pedido pedidoEncontrado = pedidoOpcional.get();
        try {
            this.repositorio.delete(pedidoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el pedido, " + error.getMessage()
            );
        }
    }

    // Actualizar datos de un pedido
    public PedidoDTO actualizarPedido(Integer id, Pedido datosActualizados) {
        Optional<Pedido> pedidoOpcional = this.repositorio.findById(id);
        if (!pedidoOpcional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el pedido con el id " + id
            );
        }
        Pedido pedidoEncontrado = pedidoOpcional.get();

        // Fecha de entrega
        pedidoEncontrado.setFechaEntrega(datosActualizados.getFechaEntrega());

        // Permitir la actualización del monto
        pedidoEncontrado.setMontoTotal(datosActualizados.getMontoTotal());

        // Costo de envío
        pedidoEncontrado.setCostoEnvio(datosActualizados.getCostoEnvio());

        // (Opcional pero recomendable: actualizar la relación Cliente)
        pedidoEncontrado.setCliente(datosActualizados.getCliente());


        // Guardar en la base de datos
        Pedido pedidoActualizado = this.repositorio.save(pedidoEncontrado);
        if (pedidoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el pedido en la base de datos"
            );
        }
        return this.pedidoMapa.convertir_pedido_a_pedidodto(pedidoActualizado);
    }

    // Buscar pedidos por fecha de creación
    public List<PedidoDTO> buscarPedidosPorFecha(LocalDate fecha) {
        List<Pedido> todosLosPedidos = this.repositorio.findAll();
        List<Pedido> pedidosPorFecha = new ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (pedido.getFechaCreacion().equals(fecha)) {
                pedidosPorFecha.add(pedido);
            }
        }

        if (pedidosPorFecha.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron pedidos en la fecha " + fecha
            );
        }
        return this.pedidoMapa.convertir_lista_a_pedidodto(pedidosPorFecha);
    }
}