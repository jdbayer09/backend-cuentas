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
@Schema(description = "Datos requeridos para crear un método de pago")
public class PaymentMethodRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3026357803278208788L;

    @Schema(description = "Nombres del método de pago")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Schema(description = "Descripción del método de pago")
    private String description;

    @Schema(description = "Icono para mostrar")
    @NotBlank(message = "El icono es requerido")
    private String icon;

    @Schema(description = "Color para identificar el método de pago")
    @NotNull(message = "El color es requerido")
    private Color color;
}