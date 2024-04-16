package com.jdbayer.cuentas.api.models.responses.admin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion de respuesta de usuario.")
public record UserBaseResponse(
        @Schema(description = "ID usuario")
        Long id,
        @Schema(description = "Nombre Completo usuario")
        String fullName,
        @Schema(description = "Correo Electronico usuario")
        String email
) {
}
