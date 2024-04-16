package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema(description = "Información de respuesta completa de una categoría")
public record CategoryResponse(
        @Schema(description = "ID categoría")
        Long id,
        @Schema(description = "Nombre categoría")
        String name,
        @Schema(description = "Color para identificar la categoría")
        String color,
        @Schema(description = "Icono para personalizar la categoría")
        String icon,
        @Schema(description = "Descripción de la categoría")
        String description,
        @Schema(description = "Bandera para identificar si esta activa la categoría")
        boolean active,
        @Schema(description = "Fecha de creación de la categoría")
        ZonedDateTime createdAt
        ) {
}
