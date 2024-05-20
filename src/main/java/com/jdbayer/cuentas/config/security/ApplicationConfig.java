package com.jdbayer.cuentas.config.security;

import com.jdbayer.cuentas.api.services.admin.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final IUserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::getUserDetailByEmail;
    }
}
