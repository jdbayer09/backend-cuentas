package com.jdbayer.cuentas.api.controllers.security;

import com.jdbayer.cuentas.api.models.responses.security.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
@Tag(name = "Servicio de seguridad", description = "Servicio para validar el usuario y la sesion de este.")
public class SecurityController {

    @GetMapping("/validate-session")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Valida la sesion del usuario.")
    public ResponseEntity<AuthenticationResponse> validateAuthentication(Authentication auth) {
        System.out.println(auth.getName());
        return ResponseEntity.ok(null);
    }
}
