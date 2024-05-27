package com.jdbayer.cuentas.api.models.requests.core;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos requeridos para crear un gasto")
public class CostRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8882725720504411508L;

    @Schema(description = "Nombres del gasto")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Schema(description = "Valor total del gasto a almacenar")
    @NotNull(message = "El gasto es requerido")
    @DecimalMin(value = "0.00", message = "No puede tener un gasto menor a 0.00")
    private BigDecimal amount;

    @Schema(description = "Mes del gasto")
    @NotNull(message = "El mes es requerido")
    @Min(value = 1, message = "El mes mínimo es 1")
    @Max(value = 12, message = "El mes máximo es 12")
    private Integer month;

    @Schema(description = "Año del gasto")
    @NotNull(message = "El año es requerido")
    @Min(value = 2022, message = "El año no puede ser menos a 2022")
    private Integer year;

    @Schema(description = "La categoría que se va a usar")
    @NotNull(message = "La categoría es requerida")
    private Long categoryId;

    @Schema(description = "El método de pago que se va a usar")
    @NotNull(message = "l método de pago es requerido")
    private Long paymentMethodId;

    @Schema(description = "Bandera que indica si desea replicar")
    @NotNull(message = "El valor de replica es requerido")
    private Boolean replicate;

    @Schema(description = "Valor que indica la cantidad de replicas que desea ingresar.")
    @NotNull(message = "El valor de replica es requerido")
    @Min(value = 0, message = "No puede ingresar un numero menor a 0.")
    @Max(value = 12, message = "No se puede replicar mas de 12 veces el ingreso.")
    private Integer replicateVal;
}
