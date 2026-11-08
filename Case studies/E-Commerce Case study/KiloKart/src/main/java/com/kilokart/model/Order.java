package com.kilokart.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderItem> items;
    private final Address shippingAddress;
    private final BigDecimal subtotal;
    private final BigDecimal shippingCost;
    private final BigDecimal discount;
    private final BigDecimal total;
    private final Instant createdAt;
    private String trackingInformation;
    private OrderStatus status;

    public Order(String id, String customerId, List<OrderItem> items, Address shippingAddress,
                 BigDecimal subtotal, BigDecimal shippingCost, BigDecimal discount,
                 BigDecimal total, OrderStatus status) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.customerId = Objects.requireNonNull(customerId, "customerId must not be null");
        this.items = List.copyOf(items);
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("order must contain at least one item");
        }
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "shippingAddress must not be null");
        this.subtotal = Objects.requireNonNull(subtotal, "subtotal must not be null");
        this.shippingCost = Objects.requireNonNull(shippingCost, "shippingCost must not be null");
        this.discount = Objects.requireNonNull(discount, "discount must not be null");
        this.total = Objects.requireNonNull(total, "total must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public BigDecimal getTotal() {
        return total;
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

    public String getTrackingInformation() {
        return trackingInformation;
    }

    public void setTrackingInformation(String trackingInformation) {
        if (status != OrderStatus.DISPATCHED && status != OrderStatus.IN_TRANSIT) {
            throw new IllegalStateException("Tracking information is available after dispatch");
        }
        if (trackingInformation == null || trackingInformation.isBlank()) {
            throw new IllegalArgumentException("trackingInformation must not be blank");
        }
        this.trackingInformation = trackingInformation.trim();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        Objects.requireNonNull(status, "status must not be null");
        if (!this.status.canTransitionTo(status)) {
            throw new IllegalStateException("Cannot change order status from " + this.status + " to " + status);
        }
        this.status = status;
    }
}
