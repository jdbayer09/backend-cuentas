package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Schema(description = "Información de respuesta de un gasto")
public record BaseCostResponse(
        @Schema(description = "Nombre gasto")
        String name,
        @Schema(description = "Valor total del gasto")
        BigDecimal amount,
        @Schema(description = "Identifica si ya se ha pagado o no el dinero")
        boolean paid,
        @Schema(description = "Fecha de creación del pago")
        ZonedDateTime createdAt
) {
}
