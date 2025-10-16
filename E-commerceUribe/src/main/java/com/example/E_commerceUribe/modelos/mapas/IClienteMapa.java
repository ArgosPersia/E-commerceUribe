package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    @Mapping( source = "id", target = "id")
    @Mapping( source = "calificacion", target = "calificacion")
    @Mapping( source = "referenciapago", target = "referenciapago")
    @Mapping( source = "departamentoCliente", target = "departamentoCliente")
    @Mapping( source = "ciudad", target = "ciudad")
    ClienteDTO convertir_cliente_a_clientedto(Cliente cliente);

    List<ClienteDTO> convertir_lista_a_clientedto(List<Cliente> lista);
}
