package com.jdbayer.cuentas.api.exceptions.util;

import java.io.Serial;

public class EmailException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -7619187515125343828L;

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }

    protected EmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
