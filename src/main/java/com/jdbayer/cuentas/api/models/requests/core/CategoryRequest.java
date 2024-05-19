package com.jdbayer.cuentas.api.models.requests.core;

import com.jdbayer.cuentas.api.models.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos requeridos para crear una categoría")
public class CategoryRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3026357803278208788L;

    @Schema(description = "Nombres de la categoría")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Schema(description = "Descripción de la categoría")
    private String description;

    @Schema(description = "Icono para mostrar")
    @NotBlank(message = "El icono es requerido")
    private String icon;

    @Schema(description = "Color para identificar la categoría")
    @NotNull(message = "El color es requerido")
    private Color color;
}