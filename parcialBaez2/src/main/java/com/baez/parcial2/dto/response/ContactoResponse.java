package com.baez.parcial2.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactoResponse {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String cargo;
}
