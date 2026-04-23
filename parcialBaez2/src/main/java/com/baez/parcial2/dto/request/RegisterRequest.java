package com.baez.parcial2.dto.request;

import com.baez.parcial2.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 80, message = "El nombre debe tener entre 3 y 80 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private Role role;
}
