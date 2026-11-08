package com.kilokart.model;

public enum OrderStatus {
    PLACED,
    CONFIRMED,
    DISPATCHED,
    IN_TRANSIT,
    DELIVERED,
    RETURN_REQUESTED,
    RETURNED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case PLACED -> next == CONFIRMED || next == CANCELLED;
            case CONFIRMED -> next == DISPATCHED || next == CANCELLED;
            case DISPATCHED -> next == IN_TRANSIT;
            case IN_TRANSIT -> next == DELIVERED;
            case DELIVERED -> next == RETURN_REQUESTED;
            case RETURN_REQUESTED -> next == RETURNED;
            case RETURNED, CANCELLED -> false;
        };
    }
}
