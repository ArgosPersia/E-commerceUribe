package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    // 1. DTO a Entidad (Para Guardar)
    @Mapping(source = "usuarioId", target = "usuario.id")
    Cliente convertir_cliente_dto_a_cliente(ClienteDTO clienteDTO);

    // 2. Entidad a DTO (Para Retornar)

    @Mapping(source = "usuario.id", target = "usuarioId")
    ClienteDTO convertir_cliente_a_clientedto(Cliente cliente);

    // 3. Lista de Entidad a Lista de DTO
    List<ClienteDTO> convertir_lista_a_listadto(List<Cliente> lista);
}