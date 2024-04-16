package com.jdbayer.cuentas.api.controllers.admin;

import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;
import com.jdbayer.cuentas.api.models.responses.admin.UserBaseResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/forgot-password/{idUser}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registro de usuarios.")
    public ResponseEntity<MessageResponse<String>> forgotPassword() {
        //TODO: Implementar funcion de olvido contraseña.
        throw new RuntimeException("Not implemented");
    }

    @PutMapping("/update-password/{idUser}/{idRequest}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registro de usuarios.")
    public ResponseEntity<MessageResponse<String>> updatePassword() {
        //TODO: Implementar update password, debe de validar que el usuario solicite el cambio de contraseña.
        throw new RuntimeException("Not implemented");
    }

    @PutMapping("/activate-user/{idRequest}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registro de usuarios.")
    public ResponseEntity<MessageResponse<String>> activateUser() {
        //TODO: Implementar activate user, debe validar que la URL enviada sea la correcta.
        throw new RuntimeException("Not implemented");
    }
}
