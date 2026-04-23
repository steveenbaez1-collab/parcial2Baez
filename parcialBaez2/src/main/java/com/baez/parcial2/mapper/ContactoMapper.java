package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.ContactoRequest;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.entity.Contacto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Contacto toEntity(ContactoRequest request);

    ContactoResponse toResponse(Contacto contacto);
}
