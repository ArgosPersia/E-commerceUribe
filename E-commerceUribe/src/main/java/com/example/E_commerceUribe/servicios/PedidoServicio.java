package com.example.E_commerceUribe.servicios;

import com.example.E_commerceUribe.modelos.Pedido;
import com.example.E_commerceUribe.modelos.DTO.PedidoDTO;
import com.example.E_commerceUribe.modelos.mapas.IPedidoMapa;
import com.example.E_commerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
