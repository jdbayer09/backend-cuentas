package com.jdbayer.cuentas.api.exceptions.core;

import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;

import java.io.Serial;

public class NotExistCashReceiptException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 492566591997427787L;

    public NotExistCashReceiptException() {
        super();
    }

    public NotExistCashReceiptException(String message) {
        super(message);
    }

    protected NotExistCashReceiptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotExistCashReceiptException(Throwable cause) {
        super(cause);
    }

    public NotExistCashReceiptException(String message, Throwable cause) {
        super(message, cause);
    }
}
