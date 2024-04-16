package com.jdbayer.cuentas.api.models.requests.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(description = "Datos requeridos para crear un usuario")
public class RegisterUserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3435879411279259898L;

    @Schema(description = "Nombres del usuario")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Schema(description = "Correo del usuario")
    @Email(message = "El formato no corresponde a un correo electronico")
    @NotBlank(message = "El correo electronico es requerido")
    private String email;

    @Schema(description = "Contraseña del usuario")
    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @Schema(description = "Confirmacion de contraseña para validar que sean iguales")
    @NotBlank(message = "Es necesario que confirme la contraseña.")
    private String confirmPassword;

    @Schema(description = "Apellidos del usuario")
    @NotBlank(message = "Es requerido los apellidos")
    private String lastName;
}
