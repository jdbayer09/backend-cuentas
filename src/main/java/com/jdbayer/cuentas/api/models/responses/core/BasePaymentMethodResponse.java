package com.jdbayer.cuentas.api.models.responses.core;

import com.jdbayer.cuentas.api.models.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de respuesta de un método de pago")
public record BasePaymentMethodResponse(
        @Schema(description = "ID método de pago")
        Long id,
        @Schema(description = "Nombre método de pago")
        String name,
        @Schema(description = "Color para identificar el método de pago")
        Color color,
        @Schema(description = "Icono para personalizar el método de pago")
        String icon,
        @Schema(description = "Fecha maxima para pagar")
        int paymentDate
) {
}
