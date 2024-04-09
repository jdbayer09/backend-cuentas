package com.jdbayer.cuentas.api.models.responses.base;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contiene la información de respuesta de los errores")
public record ErrorResponse(
        @Schema(description = "Mensaje de error")
        String errorMessage,
        @Schema(description = "Código de error")
        Integer statusCode
) {
}
