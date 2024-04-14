package com.jdbayer.cuentas.api.controllers.security;

import com.jdbayer.cuentas.api.exceptions.security.UnauthorizedException;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.responses.admin.UserBaseResponse;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.security.AuthenticationResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.config.security.jwt.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
@Tag(name = "Servicio de seguridad", description = "Servicio para validar el usuario y la sesion de este.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Credentials are required to access this resource", content =
        @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)))
})
@SecurityRequirement(name = "Bearer Authentication")
public class SecurityController {

    private final UserMapper userMapper;
    private final IUserService userService;
    private final JWTService jwtService;

    @GetMapping("/validate-session")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Valida la sesion del usuario.")
    public ResponseEntity<AuthenticationResponse> validateAuthentication(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        String token = "";

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.equalsIgnoreCase("Authorization")) {
                token = request.getHeader(headerName);
                break;
            }
        }
        if (token.isEmpty())
            throw new UnauthorizedException("No se envio un token");

        if (!jwtService.validateToken(token))
            throw new UnauthorizedException("El token enviado no es valido");

        String userName = jwtService.getUserApp(token);

        Date expirationToken = jwtService.getExpirationToken(token);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String formattedDate = sdf.format(expirationToken);

        UserBaseResponse user = userMapper.dtoToUserBaseResponse(userService.getUserDetailByEmail(userName));
        return ResponseEntity.ok(new AuthenticationResponse(token.replaceFirst(JWTService.TOKEN_PREFIX, ""), formattedDate, user));
    }
}
