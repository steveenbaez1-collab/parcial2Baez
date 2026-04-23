package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.RegisterRequest;
import com.baez.parcial2.dto.response.UsuarioResponse;
import com.baez.parcial2.entity.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-23T07:44:19-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.nombre( request.getNombre() );
        usuario.email( request.getEmail() );
        usuario.password( request.getPassword() );
        usuario.role( request.getRole() );

        return usuario.build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResponse.UsuarioResponseBuilder usuarioResponse = UsuarioResponse.builder();

        usuarioResponse.id( usuario.getId() );
        usuarioResponse.nombre( usuario.getNombre() );
        usuarioResponse.email( usuario.getEmail() );
        usuarioResponse.role( usuario.getRole() );

        return usuarioResponse.build();
    }
}
