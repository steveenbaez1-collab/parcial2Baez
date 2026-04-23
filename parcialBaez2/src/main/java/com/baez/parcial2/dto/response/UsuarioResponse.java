package com.baez.parcial2.dto.response;

import com.baez.parcial2.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private Role role;
}
