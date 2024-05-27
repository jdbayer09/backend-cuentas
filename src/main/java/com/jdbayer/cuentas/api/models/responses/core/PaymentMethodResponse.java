package com.jdbayer.cuentas.api.models.responses.core;

import com.jdbayer.cuentas.api.models.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema(description = "Información de respuesta completa de un método de pago")
public record PaymentMethodResponse(
        @Schema(description = "ID método de pago")
        Long id,
        @Schema(description = "Nombre método de pago")
        String name,
        @Schema(description = "Color para identificar el método de pago")
        Color color,
        @Schema(description = "Icono para personalizar el método de pago")
        String icon,
        @Schema(description = "Descripción del método de pago")
        String description,
        @Schema(description = "Bandera para identificar si esta activo el método de pago")
        boolean active,
        @Schema(description = "Fecha de creación del método de pago")
        ZonedDateTime createdAt,
        @Schema(description = "Fecha maxima donde se puede pagar")
        int paymentDate
        ) {
}
