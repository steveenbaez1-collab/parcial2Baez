package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.OportunidadRequest;
import com.baez.parcial2.dto.response.OportunidadResponse;
import com.baez.parcial2.entity.Oportunidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OportunidadMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    Oportunidad toEntity(OportunidadRequest request);

    OportunidadResponse toResponse(Oportunidad oportunidad);
}
