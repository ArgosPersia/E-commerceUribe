package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.ProductoDTO;
import com.example.E_commerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {
    @Mapping( source = "id", target = "id")
    @Mapping( source = "nombre", target = "nombre")
    @Mapping( source = "fotografia", target = "fotografia")
    @Mapping( source = "descripcion", target = "descripcion")
    @Mapping( source = "precioUnitario", target = "precioUnitario")
    @Mapping( source = "marca", target = "marca")
    @Mapping( source = "aplicaDescuento", target = "aplicaDescuento")
    ProductoDTO convertir_producto_a_productodto(Producto producto);

    List<ProductoDTO> convertir_lista_a_productodto(List<Producto> lista);

}
