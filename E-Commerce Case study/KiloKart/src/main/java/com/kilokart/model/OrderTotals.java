package com.kilokart.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderTotals {
    private final BigDecimal subtotal;
    private final BigDecimal shippingCost;
    private final BigDecimal discount;
    private final BigDecimal total;

    public OrderTotals(BigDecimal subtotal, BigDecimal shippingCost, BigDecimal discount) {
        this.subtotal = Objects.requireNonNull(subtotal, "subtotal must not be null");
        this.shippingCost = Objects.requireNonNull(shippingCost, "shippingCost must not be null");
        this.discount = Objects.requireNonNull(discount, "discount must not be null");
        if (subtotal.signum() < 0 || shippingCost.signum() < 0
                || discount.signum() < 0 || discount.compareTo(subtotal) > 0) {
            throw new IllegalArgumentException("Invalid order totals");
        }
        this.total = subtotal.add(shippingCost).subtract(discount);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
