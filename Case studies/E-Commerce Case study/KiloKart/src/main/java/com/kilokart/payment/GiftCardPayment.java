package com.kilokart.payment;

import java.math.BigDecimal;
import java.util.Objects;

public class GiftCardPayment implements PaymentMethod {
    private BigDecimal balance;

    public GiftCardPayment(BigDecimal balance) {
        this.balance = Objects.requireNonNull(balance, "balance must not be null");
        if (balance.signum() < 0) {
            throw new IllegalArgumentException("balance must not be negative");
        }
    }

    @Override
    public synchronized boolean pay(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() < 0 || amount.compareTo(balance) > 0) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return "Gift card";
    }
}
