package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.RegisterRequest;
import com.baez.parcial2.dto.response.UsuarioResponse;
import com.baez.parcial2.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(RegisterRequest request);
    UsuarioResponse toResponse(Usuario usuario);
}
