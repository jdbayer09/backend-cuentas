package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Schema(description = "Información de respuesta de un gasto")
public record BaseCostResponse(
        @Schema(description = "ID gasto")
        Long id,
        @Schema(description = "Nombre gasto")
        String name,
        @Schema(description = "Valor total del gasto")
        BigDecimal amount,
        @Schema(description = "Mes en el que se va a registrar el gasto")
        Integer month,
        @Schema(description = "Año en el que se va a registrar el gasto")
        Integer year,
        @Schema(description = "Identifica si ya se ha pagado o no el dinero")
        boolean paid,
        @Schema(description = "Fecha de creación del pago")
        ZonedDateTime createdAt,
        @Schema(description = "Categoría del gasto")
        BaseCategoryResponse category,
        @Schema(description = "Método de pago del gasto")
        BasePaymentMethodResponse paymentMethod
) {
}
