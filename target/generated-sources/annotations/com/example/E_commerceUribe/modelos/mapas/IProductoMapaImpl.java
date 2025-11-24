package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.ProductoDTO;
import com.example.E_commerceUribe.modelos.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T09:07:03-0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class IProductoMapaImpl implements IProductoMapa {

    @Override
    public ProductoDTO convertir_producto_a_productodto(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setId( producto.getId() );
        productoDTO.setNombre( producto.getNombre() );
        productoDTO.setFotografia( producto.getFotografia() );
        productoDTO.setDescripcion( producto.getDescripcion() );
        productoDTO.setPrecioUnitario( producto.getPrecioUnitario() );
        productoDTO.setMarca( producto.getMarca() );
        productoDTO.setAplicaDescuento( producto.getAplicaDescuento() );

        return productoDTO;
    }

    @Override
    public List<ProductoDTO> convertir_lista_a_productodto(List<Producto> lista) {
        if ( lista == null ) {
            return null;
        }

        List<ProductoDTO> list = new ArrayList<ProductoDTO>( lista.size() );
        for ( Producto producto : lista ) {
            list.add( convertir_producto_a_productodto( producto ) );
        }

        return list;
    }
}
