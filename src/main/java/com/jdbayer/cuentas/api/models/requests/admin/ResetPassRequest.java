package com.jdbayer.cuentas.api.models.requests.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Datos Requeridos para actualizar contraseña")
public class ResetPassRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7462245974955185137L;

    @Schema(description = "Contraseña del usuario")
    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @Schema(description = "Confirmacion de contraseña para validar que sean iguales")
    @NotBlank(message = "Es necesario que confirme la contraseña.")
    private String confirmPassword;
}
