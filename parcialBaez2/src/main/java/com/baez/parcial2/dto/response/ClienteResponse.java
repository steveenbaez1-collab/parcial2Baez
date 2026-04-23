package com.baez.parcial2.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String nit;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private String vendedorNombre;
    private List<ContactoResponse> contactos;
    private List<OportunidadResponse> oportunidades;
}
