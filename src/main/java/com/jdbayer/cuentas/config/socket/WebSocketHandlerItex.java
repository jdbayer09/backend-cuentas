package com.jdbayer.cuentas.config.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbayer.cuentas.config.security.jwt.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandlerItex extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = new HashSet<>();
    private final ObjectMapper mapper;
    private final JWTService jwtService;
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        WebSocketMessage<String> data = new WebSocketMessage<>("Error al ingresar al socket", WebSocketMessageType.ERROR_SOCKET);
        data.setSessionId(session.getId());
        String jsonData = mapper.writeValueAsString(data);
        TextMessage message = new TextMessage(jsonData);
        String token = Objects.requireNonNull(session.getUri()).getQuery().replace("token=", "");
        if (token.isEmpty()) {
            sendOneMessage(session, message);
        } else  {
            if (jwtService.validateToken(JWTService.TOKEN_PREFIX + token)) {
                sessions.add(session);
            } else {
                sendOneMessage(session, message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,@NonNull CloseStatus status) throws Exception {
        sessions.remove(session);
        session.close(status);
    }

    public <T> void sendMessage(WebSocketMessage<T> data) {
        try {
            for (WebSocketSession session: sessions) {
                data.setSessionId(session.getId());
                String jsonData = mapper.writeValueAsString(data);
                TextMessage message = new TextMessage(jsonData);
                sendOneMessage(session, message);
            }
        } catch (JsonProcessingException ex) {
          log.error("Error when mapping the object", ex);
        }
    }

    private void sendOneMessage(WebSocketSession session, TextMessage message) {
        try {
            session.sendMessage(message);
        } catch (IOException ex) {
            log.error("Could not send message via webSocket", ex);
        }
    }
}
