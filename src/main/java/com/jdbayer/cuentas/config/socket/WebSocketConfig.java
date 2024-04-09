package com.jdbayer.cuentas.config.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbayer.cuentas.config.security.jwt.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final JWTService jwtService;

    @Autowired
    public WebSocketConfig(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandlerItex(), "/websocket").setAllowedOrigins("*");
    }

    public WebSocketHandler webSocketHandlerItex() {
        return new WebSocketHandlerItex(new ObjectMapper(), jwtService);
    }
}
