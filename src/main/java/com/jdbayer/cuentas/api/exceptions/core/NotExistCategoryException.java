package com.jdbayer.cuentas.api.exceptions.core;

import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;

import java.io.Serial;

public class NotExistCategoryException extends NotFoundException {
    @Serial
    private static final long serialVersionUID = -3284995402633953514L;

    public NotExistCategoryException() {
        super();
    }

    public NotExistCategoryException(String message) {
        super(message);
    }

    protected NotExistCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotExistCategoryException(Throwable cause) {
        super(cause);
    }

    public NotExistCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
