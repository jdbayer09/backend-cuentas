package com.jdbayer.cuentas.config.controller;

import com.jdbayer.cuentas.api.exceptions.base.BadRequestException;
import com.jdbayer.cuentas.api.exceptions.base.NotFoundException;
import com.jdbayer.cuentas.api.exceptions.util.EmailException;
import com.jdbayer.cuentas.api.exceptions.security.UnauthorizedException;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handlerUncaughtException(Throwable t) {
        return buildErrorResponse(t, t.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> invalidRequestErrorHandler(final MethodArgumentNotValidException e) {

        var errors =
                e.getBindingResult().getAllErrors().stream()
                        .filter(Objects::nonNull)
                        .map(this::getValidationErrorMessage)
                        .toList();
        return buildErrorResponse(e, String.join(", ", errors), PRECONDITION_FAILED);
    }

    public String getValidationErrorMessage(final ObjectError error) {
        final var errorMessage = new StringBuilder();
        if (error instanceof FieldError fe) {
            errorMessage.append("<").append(fe.getField()).append("> - ");
        }
        errorMessage.append(error.getDefaultMessage());
        return errorMessage.toString();
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerObjectNotFoundException(final ObjectNotFoundException t) {
        return buildErrorResponse(t, t.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(final NotFoundException t) {
        return buildErrorResponse(t, t.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlerBadRequestException(final BadRequestException t) {
        return buildErrorResponse(t, t.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handlerUnauthorizedException(final UnauthorizedException t) {
        return buildErrorResponse(t, t.getMessage(), UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> handlerIllegalArgumentException(final IllegalArgumentException t) {
        return buildErrorResponse(t, t.getMessage(), PRECONDITION_FAILED);
    }

    @ExceptionHandler(EmailException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handlerEmailException(final EmailException t) {
        return buildErrorResponse(t, t.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> handlerDataIntegrityViolationException(final DataIntegrityViolationException t) {
        String message;
        try {
            message = messageFkUnique(t.getCause().getCause().getMessage());
        } catch (Exception ex) {
            message = "Unexpected error: " + t.getCause().getCause().getMessage();
        }

        return buildErrorResponse(t, message, PRECONDITION_FAILED);
    }

    private String messageFkUnique(String error) {
        var message = "Unexpected error: " + error;
        var errors = error.split("\\(")[2].split("\\)");
        var val = errors[0];

        if (error.contains("user_unique_email"))
            message = "El Correo Electrónico introducido ya está registrado (" + val + ")";
        else if (error.contains("category_unique_name"))
            message = "Esta categoría ya esta registrada (" + val + ")";
        else if (error.contains("payment_methods_unique_name"))
            message = "Este método de pago ya esta registrado (" + val + ")";
        else if (error.contains("cash_receipts_unique_name"))
            message = "Ya hay un ingreso registrado con el siguiente valor (" + val + ")";
        return message;
    }

    /**
     * Builds the {@code ErrorResponse} object to serve all error request and response generic message
     *
     * @param e          Exception thrown by the handler itself
     * @param message    Message to be shown in the consumer request
     * @param httpStatus HTTP status to be sent it to the consumer
     * @return ErrorRepose
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Throwable e, String message, HttpStatus httpStatus) {
        log.error(message, e);
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(message, httpStatus.value()));
    }
}