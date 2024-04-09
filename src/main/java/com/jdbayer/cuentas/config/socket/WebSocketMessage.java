package com.jdbayer.cuentas.config.socket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class WebSocketMessage<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5942088148865685998L;

    private T data;
    private WebSocketMessageType type;
    private String sessionId;

    public WebSocketMessage(T data, WebSocketMessageType type) {
        this.data = data;
        this.type = type;
    }

}
