package com.kilokart.exception;

public class AuthenticationException extends KiloKartException {
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String message) {
        super(message);
    }
}
