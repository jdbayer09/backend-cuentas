package com.jdbayer.cuentas.api.exceptions.core;

import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;

import java.io.Serial;

public class NotExistPaymentMethodException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -3050716546984058812L;

    public NotExistPaymentMethodException() {
        super();
    }

    public NotExistPaymentMethodException(String message) {
        super(message);
    }

    protected NotExistPaymentMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotExistPaymentMethodException(Throwable cause) {
        super(cause);
    }

    public NotExistPaymentMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
