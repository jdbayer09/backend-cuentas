package com.jdbayer.cuentas.config.security.jwt.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public interface JWTService {
    String TOKEN_PREFIX = "Bearer ";
    String createToken(Authentication auth) throws IOException;
    boolean validateToken(String token);
    String getUserApp(String token);
    Collection<SimpleGrantedAuthority> getRoles(String token) throws IOException;
    Date getExpirationToken(String token);
}
