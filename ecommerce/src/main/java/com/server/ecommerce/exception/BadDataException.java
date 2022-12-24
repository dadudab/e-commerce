package com.server.ecommerce.exception;

public class BadDataException extends RuntimeException {
    public BadDataException(String message) {
        super(message);
    }
}
