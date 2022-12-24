package com.server.ecommerce.error;

import com.server.ecommerce.exception.AlreadyExistsException;
import com.server.ecommerce.exception.BadDataException;
import com.server.ecommerce.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvise {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataNotFoundException.class)
    public ErrorResponse dataNotFoundException(DataNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadDataException.class)
    public ErrorResponse badDataException(BadDataException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ErrorResponse alreadyExistsException(AlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return errorResponse;
    }
}
