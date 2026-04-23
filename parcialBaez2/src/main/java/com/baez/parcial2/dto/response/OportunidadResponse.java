package com.baez.parcial2.dto.response;

import com.baez.parcial2.entity.EtapaOportunidad;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class OportunidadResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal monto;
    private EtapaOportunidad etapa;
    private LocalDate fechaCierreEstimada;
}
