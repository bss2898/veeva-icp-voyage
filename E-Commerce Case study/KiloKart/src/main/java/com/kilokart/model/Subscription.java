package com.kilokart.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Subscription {
    private final String id = UUID.randomUUID().toString();
    private final String customerId;
    private final SubscriptionDuration duration;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private SubscriptionStatus status;

    public Subscription(String customerId, SubscriptionDuration duration, LocalDate startDate) {
        this.customerId = Objects.requireNonNull(customerId, "customerId must not be null");
        this.duration = Objects.requireNonNull(duration, "duration must not be null");
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.endDate = startDate.plus(duration.getPeriod());
        this.status = SubscriptionStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public SubscriptionDuration getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SubscriptionStatus getStatus() {
        refreshStatus();
        return status;
    }

    public boolean isActive() {
        refreshStatus();
        return status == SubscriptionStatus.ACTIVE;
    }

    public void cancel() {
        if (status == SubscriptionStatus.ACTIVE) {
            status = SubscriptionStatus.CANCELLED;
        }
    }

    private void refreshStatus() {
        if (status == SubscriptionStatus.ACTIVE && !LocalDate.now().isBefore(endDate)) {
            status = SubscriptionStatus.EXPIRED;
        }
    }
}
