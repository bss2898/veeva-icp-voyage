package com.kilokart.payment;

import java.math.BigDecimal;
import java.util.Objects;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean pay(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        return amount.signum() >= 0;
    }

    @Override
    public String getName() {
        return "Credit card (simulated)";
    }
}
