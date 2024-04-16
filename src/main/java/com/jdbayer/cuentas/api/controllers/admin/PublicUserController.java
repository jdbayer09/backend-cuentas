package com.jdbayer.cuentas.api.controllers.admin;

import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;
import com.jdbayer.cuentas.api.models.requests.admin.ResetPassRequest;
import com.jdbayer.cuentas.api.models.responses.admin.UserBaseResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
@RequiredArgsConstructor
@Tag(name = "Servicios publico de Usuarios", description = "Servicios públicos para registro y gestión de la contraseña")
public class PublicUserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registro de usuarios.")
    public ResponseEntity<MessageResponse<UserBaseResponse>> registerUser(
            @RequestBody @Valid RegisterUserRequest registerUserRequest
    ) {
        var userDto = userService.registerUser(registerUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
          new MessageResponse<>(
                  "Exito!",
                  "El usuario se ha creado de forma correcta, verifica el correo electrónico para poder acceder a su cuenta.",
                  userMapper.dtoToUserBaseResponse(userDto)
          )
        );
    }

    @PutMapping("/activate-user/{activationCode}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Activacion de usuario")
    public ResponseEntity<MessageResponse<UserBaseResponse>> activateUser(@PathVariable String activationCode) {
        var userDto = userService.activateUser(activationCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse<>(
                        "Exito!",
                        userDto.getFullName() + ", Se ha activado el usuario correctamente.",
                        userMapper.dtoToUserBaseResponse(userDto)
                )
        );
    }

    @PutMapping("/forgot-password/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Solicitar cambio de contraseña")
    public ResponseEntity<MessageResponse<UserBaseResponse>> forgotPassword(@PathVariable String email) {
        var userDto = userService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse<>(
                        "Exito!",
                        userDto.getFullName() + ", Se ha enviado un enlace para actualizar su contraseña.",
                        userMapper.dtoToUserBaseResponse(userDto)
                )
        );
    }

    @PutMapping("/change-password/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cambiar la contraseña")
    public ResponseEntity<MessageResponse<UserBaseResponse>> updatePassword(
            @PathVariable String code,
            @RequestBody @Valid ResetPassRequest request
    ) {
        var userDto = userService.resetPassword(code, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse<>(
                        "Exito!",
                        "Se ha actualizado la contraseña correctamente.",
                        userMapper.dtoToUserBaseResponse(userDto)
                )
        );
    }
}
