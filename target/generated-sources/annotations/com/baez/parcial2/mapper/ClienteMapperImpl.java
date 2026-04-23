package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.ClienteRequest;
import com.baez.parcial2.dto.response.ClienteResponse;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.dto.response.OportunidadResponse;
import com.baez.parcial2.entity.Cliente;
import com.baez.parcial2.entity.Contacto;
import com.baez.parcial2.entity.Oportunidad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-23T07:44:18-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Autowired
    private ContactoMapper contactoMapper;
    @Autowired
    private OportunidadMapper oportunidadMapper;

    @Override
    public Cliente toEntity(ClienteRequest request) {
        if ( request == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.nombre( request.getNombre() );
        cliente.nit( request.getNit() );
        cliente.email( request.getEmail() );
        cliente.telefono( request.getTelefono() );
        cliente.direccion( request.getDireccion() );

        return cliente.build();
    }

    @Override
    public ClienteResponse toResponse(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteResponse.ClienteResponseBuilder clienteResponse = ClienteResponse.builder();

        clienteResponse.id( cliente.getId() );
        clienteResponse.nombre( cliente.getNombre() );
        clienteResponse.nit( cliente.getNit() );
        clienteResponse.email( cliente.getEmail() );
        clienteResponse.telefono( cliente.getTelefono() );
        clienteResponse.direccion( cliente.getDireccion() );
        clienteResponse.fechaCreacion( cliente.getFechaCreacion() );
        clienteResponse.contactos( contactoListToContactoResponseList( cliente.getContactos() ) );
        clienteResponse.oportunidades( oportunidadListToOportunidadResponseList( cliente.getOportunidades() ) );

        clienteResponse.vendedorNombre( cliente.getVendedor() != null ? cliente.getVendedor().getNombre() : null );

        return clienteResponse.build();
    }

    protected List<ContactoResponse> contactoListToContactoResponseList(List<Contacto> list) {
        if ( list == null ) {
            return null;
        }

        List<ContactoResponse> list1 = new ArrayList<ContactoResponse>( list.size() );
        for ( Contacto contacto : list ) {
            list1.add( contactoMapper.toResponse( contacto ) );
        }

        return list1;
    }

    protected List<OportunidadResponse> oportunidadListToOportunidadResponseList(List<Oportunidad> list) {
        if ( list == null ) {
            return null;
        }

        List<OportunidadResponse> list1 = new ArrayList<OportunidadResponse>( list.size() );
        for ( Oportunidad oportunidad : list ) {
            list1.add( oportunidadMapper.toResponse( oportunidad ) );
        }

        return list1;
    }
}
