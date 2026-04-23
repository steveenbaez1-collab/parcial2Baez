package com.baez.parcial2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @NotBlank(message = "El NIT es obligatorio")
    @Size(max = 30, message = "El NIT no puede superar 30 caracteres")
    private String nit;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 180, message = "La dirección no puede superar 180 caracteres")
    private String direccion;
}
