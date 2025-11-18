package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.PedidoDTO;
import com.example.E_commerceUribe.modelos.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T08:00:32-0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class IPedidoMapaImpl implements IPedidoMapa {

    @Override
    public PedidoDTO convertir_pedido_a_pedidodto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId( pedido.getId() );
        pedidoDTO.setMontoTotal( pedido.getMontoTotal() );
        pedidoDTO.setFechaEntrega( pedido.getFechaEntrega() );
        pedidoDTO.setCostoEnvio( pedido.getCostoEnvio() );

        return pedidoDTO;
    }

    @Override
    public List<PedidoDTO> convertir_lista_a_pedidodto(List<Pedido> lista) {
        if ( lista == null ) {
            return null;
        }

        List<PedidoDTO> list = new ArrayList<PedidoDTO>( lista.size() );
        for ( Pedido pedido : lista ) {
            list.add( convertir_pedido_a_pedidodto( pedido ) );
        }

        return list;
    }
}
