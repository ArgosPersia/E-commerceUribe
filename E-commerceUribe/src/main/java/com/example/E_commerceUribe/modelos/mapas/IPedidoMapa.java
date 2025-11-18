package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.PedidoDTO;
import com.example.E_commerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPedidoMapa {

    @Mapping( source = "id", target = "id")
    // CORRECCIÓN CLAVE: El source debe ser 'montoTotal' (sin la 'n' en "monton")
    @Mapping( source = "montoTotal", target = "montoTotal")
    @Mapping( source = "fechaEntrega", target = "fechaEntrega")
    @Mapping( source = "costoEnvio", target = "costoEnvio")
    PedidoDTO convertir_pedido_a_pedidodto(Pedido pedido);

    List<PedidoDTO> convertir_lista_a_pedidodto(List<Pedido> lista);
}