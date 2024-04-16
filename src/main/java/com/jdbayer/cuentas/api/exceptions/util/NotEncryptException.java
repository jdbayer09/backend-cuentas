package com.jdbayer.cuentas.api.exceptions.util;

import java.io.Serial;

public class NotEncryptException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7382836471093186010L;

    public NotEncryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEncryptException(String message) {
        super(message);
    }

    public NotEncryptException() {
        super();
    }

    public NotEncryptException(Throwable cause) {
        super(cause);
    }

    protected NotEncryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
