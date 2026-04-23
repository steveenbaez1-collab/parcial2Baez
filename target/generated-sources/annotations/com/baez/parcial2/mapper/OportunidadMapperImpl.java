package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.OportunidadRequest;
import com.baez.parcial2.dto.response.OportunidadResponse;
import com.baez.parcial2.entity.Oportunidad;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-23T07:44:19-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class OportunidadMapperImpl implements OportunidadMapper {

    @Override
    public Oportunidad toEntity(OportunidadRequest request) {
        if ( request == null ) {
            return null;
        }

        Oportunidad.OportunidadBuilder oportunidad = Oportunidad.builder();

        oportunidad.nombre( request.getNombre() );
        oportunidad.descripcion( request.getDescripcion() );
        oportunidad.monto( request.getMonto() );
        oportunidad.etapa( request.getEtapa() );
        oportunidad.fechaCierreEstimada( request.getFechaCierreEstimada() );

        return oportunidad.build();
    }

    @Override
    public OportunidadResponse toResponse(Oportunidad oportunidad) {
        if ( oportunidad == null ) {
            return null;
        }

        OportunidadResponse.OportunidadResponseBuilder oportunidadResponse = OportunidadResponse.builder();

        oportunidadResponse.id( oportunidad.getId() );
        oportunidadResponse.nombre( oportunidad.getNombre() );
        oportunidadResponse.descripcion( oportunidad.getDescripcion() );
        oportunidadResponse.monto( oportunidad.getMonto() );
        oportunidadResponse.etapa( oportunidad.getEtapa() );
        oportunidadResponse.fechaCierreEstimada( oportunidad.getFechaCierreEstimada() );

        return oportunidadResponse.build();
    }
}
