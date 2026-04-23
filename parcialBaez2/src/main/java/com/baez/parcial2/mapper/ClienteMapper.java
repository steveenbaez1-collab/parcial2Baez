package com.baez.parcial2.mapper;

import com.baez.parcial2.dto.request.ClienteRequest;
import com.baez.parcial2.dto.response.ClienteResponse;
import com.baez.parcial2.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ContactoMapper.class, OportunidadMapper.class})
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "contactos", ignore = true)
    @Mapping(target = "oportunidades", ignore = true)
    Cliente toEntity(ClienteRequest request);

    @Mapping(target = "vendedorNombre", expression = "java(cliente.getVendedor() != null ? cliente.getVendedor().getNombre() : null)")
    ClienteResponse toResponse(Cliente cliente);
}
