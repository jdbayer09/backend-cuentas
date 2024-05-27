package com.jdbayer.cuentas.api.exceptions.core;

import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;

import java.io.Serial;

public class NotExistCostException extends NotFoundException {
    @Serial
    private static final long serialVersionUID = 8621939706166157812L;

    public NotExistCostException() {
        super();
    }

    public NotExistCostException(String message) {
        super(message);
    }

    public NotExistCostException(Throwable cause) {
        super(cause);
    }

    protected NotExistCostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotExistCostException(String message, Throwable cause) {
        super(message, cause);
    }
}
