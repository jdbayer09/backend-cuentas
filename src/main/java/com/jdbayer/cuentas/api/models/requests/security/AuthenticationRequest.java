package com.jdbayer.cuentas.api.models.requests.security;

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
@Schema(description = "Datos para inicio de sesión")
public class AuthenticationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5239414195141288589L;

    @NotBlank(message = "El correo electrónico no puede ser vacío")
    @Email(message = "El correo electrónico no es valido")
    @Schema(description = "Correo electrónico del usuario")
    private String email;

    @NotBlank(message = "La contraseña no puede ser vacía")
    @Schema(description = "Contraseña creada por el usuario")
    private String password;
}