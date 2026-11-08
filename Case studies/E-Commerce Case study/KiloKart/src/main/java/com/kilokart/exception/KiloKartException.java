package com.kilokart.exception;

public class KiloKartException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KiloKartException(String message) {
        super(message);
    }
}
