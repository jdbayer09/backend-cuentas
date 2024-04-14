package com.jdbayer.cuentas.config.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.requests.security.AuthenticationRequest;
import com.jdbayer.cuentas.api.models.responses.admin.UserBaseResponse;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.security.AuthenticationResponse;
import com.jdbayer.cuentas.config.security.jwt.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        this.jwtService = jwtService;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthenticationRequest authenticationRequest;
        Authentication authResp;
        try {
            authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
        } catch (IOException e) {
            throw new UsernameNotFoundException("Problemas al leer los datos, comprueba que todos son correctos: 'E-mail', 'Password'");
        }

        try {
            authResp = authManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authenticationRequest.getEmail(),
                                    authenticationRequest.getPassword()
                            )
                    );
        } catch (AuthenticationException ae) {
            if (ae.getMessage().equals("User is disabled"))
                throw new UsernameNotFoundException("El usuario está deshabilitado.", ae);
            else if (ae.getMessage().equals("Bad credentials"))
                throw new UsernameNotFoundException("¡Malas credenciales!", ae);
            else
                throw new UsernameNotFoundException("¡Error al introducir sus credenciales!", ae);
        }
        return authResp;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        UserDetailDTO userData = ((UserDetailDTO) authResult.getPrincipal());

        String token = jwtService.createToken(authResult);
        response.getWriter().write(new ObjectMapper().writeValueAsString(getAuthenticationResponse(token, userData)));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
    }

    private AuthenticationResponse getAuthenticationResponse(String token, UserDetailDTO userData) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date expirationToken = new Date(jwtService.getExpirationTokenMillis());
        var user = new UserBaseResponse(userData.getId(), userData.getFullName(), userData.getEmail());

        return new AuthenticationResponse(token, sdf.format(expirationToken), user);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new ErrorResponse(
                                "¡Credenciales inválidas! \n " + failed.getMessage(),
                                HttpStatus.FORBIDDEN.value()
                        )
                )
        );
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
    }
}
