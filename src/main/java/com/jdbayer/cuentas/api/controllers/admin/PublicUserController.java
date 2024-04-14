package com.jdbayer.cuentas.api.controllers.admin;

import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
@RequiredArgsConstructor
@Tag(name = "Servicios publico de Usuarios", description = "Servicios públicos para registro y gestión de la contraseña")
public class PublicUserController {
/*
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registro de usuarios.")
    public ResponseEntity<MessageResponse<UserResponse>> registerUser(
            @RequestBody @Valid UserRequest user
    ) {
        var userDTO = userService.create(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new MessageResponse<>(
                                SUCCESS_TITTLE,
                                "The user has been successfully created, the access data has been sent to the e-mail address",
                                mapper.dtoToResponse(userDTO)
                        )
                );
    } */
}
