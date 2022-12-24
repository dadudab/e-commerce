package com.server.ecommerce.exception;

public class AuthBadCredentialsException extends RuntimeException {
    public AuthBadCredentialsException(String message) {
        super(message);
    }
}
