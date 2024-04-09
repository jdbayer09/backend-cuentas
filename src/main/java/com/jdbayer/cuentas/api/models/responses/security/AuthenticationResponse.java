package com.jdbayer.cuentas.api.models.responses.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Devuelve la información de autenticación")
public record AuthenticationResponse(
        @Schema(description = "Token de autorización")
        String token,
        @Schema(description = "Fecha de expiración de la sesión")
        String expirationToken
) {
}
