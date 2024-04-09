package com.jdbayer.cuentas.api.exceptions.base;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8582134404449604261L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
