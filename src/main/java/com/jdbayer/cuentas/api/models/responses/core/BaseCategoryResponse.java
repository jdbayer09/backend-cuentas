package com.jdbayer.cuentas.api.models.responses.core;

import com.jdbayer.cuentas.api.models.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de respuesta de una categoría")
public record BaseCategoryResponse(
        @Schema(description = "ID categoría")
        Long id,
        @Schema(description = "Nombre categoría")
        String name,
        @Schema(description = "Color para identificar la categoría")
        Color color,
        @Schema(description = "Icono para personalizar la categoría")
        String icon
) {
}
