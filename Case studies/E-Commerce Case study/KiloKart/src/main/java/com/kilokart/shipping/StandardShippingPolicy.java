package com.kilokart.shipping;

import java.math.BigDecimal;
import java.util.Objects;

public class StandardShippingPolicy implements ShippingPolicy {
    private final BigDecimal shippingFee;
    private final BigDecimal freeShippingThreshold;

    public StandardShippingPolicy(BigDecimal shippingFee, BigDecimal freeShippingThreshold) {
        this.shippingFee = Objects.requireNonNull(shippingFee, "shippingFee must not be null");
        this.freeShippingThreshold = Objects.requireNonNull(
                freeShippingThreshold, "freeShippingThreshold must not be null");
        if (shippingFee.signum() < 0 || freeShippingThreshold.signum() < 0) {
            throw new IllegalArgumentException("Shipping values must not be negative");
        }
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal, boolean subscriber) {
        Objects.requireNonNull(subtotal, "subtotal must not be null");
        if (subscriber || subtotal.compareTo(freeShippingThreshold) >= 0) {
            return BigDecimal.ZERO;
        }
        return shippingFee;
    }
}
