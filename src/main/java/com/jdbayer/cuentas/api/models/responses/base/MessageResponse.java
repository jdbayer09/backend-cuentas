package com.jdbayer.cuentas.api.models.responses.base;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de respuesta de los servicios")
public record MessageResponse<T>(

        @Schema(description = "Titulo para mostrar en el mensaje")
        String tittle,
        @Schema(description = "Mensaje que se enviara")
        String message,
        @Schema(description = "Datos que serán enviados.")
        T data
) {
}