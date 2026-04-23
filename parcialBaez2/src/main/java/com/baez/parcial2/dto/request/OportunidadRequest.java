package com.baez.parcial2.dto.request;

import com.baez.parcial2.entity.EtapaOportunidad;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OportunidadRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
    private String descripcion;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que 0")
    private BigDecimal monto;

    @NotNull(message = "La etapa es obligatoria")
    private EtapaOportunidad etapa;

    @NotNull(message = "La fecha de cierre es obligatoria")
    @FutureOrPresent(message = "La fecha de cierre debe ser presente o futura")
    private LocalDate fechaCierreEstimada;
}
