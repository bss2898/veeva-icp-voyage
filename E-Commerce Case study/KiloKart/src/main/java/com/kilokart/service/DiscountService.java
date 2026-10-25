package com.kilokart.service;

import com.kilokart.exception.KiloKartException;
import com.kilokart.model.Customer;
import com.kilokart.model.Order;
import com.kilokart.model.OrderStatus;
import com.kilokart.repository.OrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class DiscountService {
    private final OrderRepository orders;
    private final int qualifyingOrderThreshold;
    private final BigDecimal percentage;
    private final String couponCode;
    private final Set<String> usedCustomerIds = new HashSet<>();

    public DiscountService(OrderRepository orders, int qualifyingOrderThreshold,
                           BigDecimal percentage, String couponCode) {
        this.orders = Objects.requireNonNull(orders, "orders must not be null");
        if (qualifyingOrderThreshold < 0 || percentage.signum() < 0
                || percentage.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Invalid discount configuration");
        }
        this.qualifyingOrderThreshold = qualifyingOrderThreshold;
        this.percentage = percentage;
        this.couponCode = Objects.requireNonNull(couponCode, "couponCode must not be null")
                .toUpperCase(Locale.ROOT);
    }

    public BigDecimal calculateDiscount(Customer customer, BigDecimal subtotal, String enteredCode) {
        Objects.requireNonNull(customer, "customer must not be null");
        Objects.requireNonNull(subtotal, "subtotal must not be null");
        if (enteredCode == null || enteredCode.isBlank()) {
            return BigDecimal.ZERO;
        }
        if (!couponCode.equals(enteredCode.trim().toUpperCase(Locale.ROOT))) {
            throw new KiloKartException("Coupon code is invalid");
        }
        if (usedCustomerIds.contains(customer.getId())) {
            throw new KiloKartException("This coupon has already been used by this customer");
        }
        long qualifyingOrders = orders.findByCustomerId(customer.getId()).stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .count();
        if (qualifyingOrders < qualifyingOrderThreshold) {
            throw new KiloKartException("Coupon requires " + qualifyingOrderThreshold
                    + " previous qualifying orders");
        }
        return subtotal.multiply(percentage).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public void markCouponUsed(Customer customer, String enteredCode) {
        if (enteredCode != null && !enteredCode.isBlank()
                && couponCode.equals(enteredCode.trim().toUpperCase(Locale.ROOT))) {
            usedCustomerIds.add(customer.getId());
        }
    }
}
