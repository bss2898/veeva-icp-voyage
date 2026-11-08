package com.kilokart.exception;

public class PaymentFailedException extends KiloKartException {
    private static final long serialVersionUID = 1L;

    public PaymentFailedException(String message) {
        super(message);
    }
}
