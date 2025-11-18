package com.example.E_commerceUribe.modelos.mapas;

import com.example.E_commerceUribe.modelos.Cliente;
import com.example.E_commerceUribe.modelos.DTO.ClienteDTO;
import com.example.E_commerceUribe.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T08:00:33-0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class IClienteMapaImpl implements IClienteMapa {

    @Override
    public Cliente convertir_cliente_dto_a_cliente(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setUsuario( clienteDTOToUsuario( clienteDTO ) );
        cliente.setId( clienteDTO.getId() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setCalificacion( clienteDTO.getCalificacion() );
        cliente.setReferenciaPago( clienteDTO.getReferenciaPago() );
        cliente.setDepartamentoCliente( clienteDTO.getDepartamentoCliente() );
        cliente.setCiudad( clienteDTO.getCiudad() );

        return cliente;
    }

    @Override
    public ClienteDTO convertir_cliente_a_clientedto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setUsuarioId( clienteUsuarioId( cliente ) );
        clienteDTO.setId( cliente.getId() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setCalificacion( cliente.getCalificacion() );
        clienteDTO.setReferenciaPago( cliente.getReferenciaPago() );
        clienteDTO.setDepartamentoCliente( cliente.getDepartamentoCliente() );
        clienteDTO.setCiudad( cliente.getCiudad() );

        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> convertir_lista_a_listadto(List<Cliente> lista) {
        if ( lista == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( lista.size() );
        for ( Cliente cliente : lista ) {
            list.add( convertir_cliente_a_clientedto( cliente ) );
        }

        return list;
    }

    protected Usuario clienteDTOToUsuario(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( clienteDTO.getUsuarioId() );

        return usuario;
    }

    private Integer clienteUsuarioId(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }
        Usuario usuario = cliente.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Integer id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
