package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.ContactoRequest;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.entity.Contacto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-23T07:44:19-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class ContactoMapperImpl implements ContactoMapper {

    @Override
    public Contacto toEntity(ContactoRequest request) {
        if ( request == null ) {
            return null;
        }

        Contacto.ContactoBuilder contacto = Contacto.builder();

        contacto.nombre( request.getNombre() );
        contacto.email( request.getEmail() );
        contacto.telefono( request.getTelefono() );
        contacto.cargo( request.getCargo() );

        return contacto.build();
    }

    @Override
    public ContactoResponse toResponse(Contacto contacto) {
        if ( contacto == null ) {
            return null;
        }

        ContactoResponse.ContactoResponseBuilder contactoResponse = ContactoResponse.builder();

        contactoResponse.id( contacto.getId() );
        contactoResponse.nombre( contacto.getNombre() );
        contactoResponse.email( contacto.getEmail() );
        contactoResponse.telefono( contacto.getTelefono() );
        contactoResponse.cargo( contacto.getCargo() );

        return contactoResponse.build();
    }
}
