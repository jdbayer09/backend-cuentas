package com.jdbayer.cuentas.api.exceptions.admin;



import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;

import java.io.Serial;

public class NotExistUserException extends NotFoundException {
    @Serial
    private static final long serialVersionUID = -920177879254075240L;

    public NotExistUserException() {
        super();
    }

    public NotExistUserException(String message) {
        super(message);
    }

    public NotExistUserException(Throwable cause) {
        super(cause);
    }

    public NotExistUserException(String message, Throwable cause) {
        super(message, cause);
    }

    protected NotExistUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
