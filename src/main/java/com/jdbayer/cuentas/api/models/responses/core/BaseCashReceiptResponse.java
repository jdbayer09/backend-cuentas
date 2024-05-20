package com.jdbayer.cuentas.api.models.responses.core;

import com.jdbayer.cuentas.api.models.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de respuesta de un ingreso")
public record BaseCashReceiptResponse(
        @Schema(description = "ID ingreso")
        Long id,
        @Schema(description = "Nombre ingreso")
        String name,
        @Schema(description = "Color para identificar el ingreso")
        Color color,
        @Schema(description = "Valor total del ingreso")
        BigDecimal amount,
        @Schema(description = "Mes en el que se va a registrar el ingreso")
        Integer month,
        @Schema(description = "Año en el que se va a registrar el ingreso")
        Integer year,
        @Schema(description = "Identifica si ya se ha pagado o no el dinero")
        boolean paid
) {
}
