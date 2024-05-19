package com.jdbayer.cuentas.api.exceptions.admin;

import com.jdbayer.cuentas.api.exceptions.base.BadRequestException;

import java.io.Serial;

public class NotPasswordEqualsException extends BadRequestException {
    @Serial
    private static final long serialVersionUID = 1581609311497335739L;

    public NotPasswordEqualsException() {
        super();
    }

    public NotPasswordEqualsException(String message) {
        super(message);
    }

    public NotPasswordEqualsException(String message, Throwable cause) {
        super(message, cause);
    }

    protected NotPasswordEqualsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotPasswordEqualsException(Throwable cause) {
        super(cause);
    }
}
