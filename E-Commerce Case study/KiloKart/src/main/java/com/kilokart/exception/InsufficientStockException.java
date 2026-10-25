package com.kilokart.exception;

public class InsufficientStockException extends KiloKartException {
    private static final long serialVersionUID = 1L;

    public InsufficientStockException(String message) {
        super(message);
    }
}
