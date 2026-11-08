package com.kilokart.service;

import com.kilokart.model.Subscription;
import com.kilokart.model.SubscriptionDuration;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SubscriptionService {
    private final Map<String, Subscription> subscriptions = new HashMap<>();

    public Subscription subscribe(String customerId, SubscriptionDuration duration) {
        Subscription existing = subscriptions.get(customerId);
        if (existing != null && existing.isActive()) {
            throw new IllegalStateException("Customer already has an active subscription");
        }
        Subscription subscription = new Subscription(customerId, duration, LocalDate.now());
        subscriptions.put(customerId, subscription);
        return subscription;
    }

    public boolean hasActiveSubscription(String customerId) {
        Subscription subscription = subscriptions.get(customerId);
        return subscription != null && subscription.isActive();
    }

    public Optional<Subscription> getSubscription(String customerId) {
        return Optional.ofNullable(subscriptions.get(customerId));
    }
}
