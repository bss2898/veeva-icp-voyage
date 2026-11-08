package com.kilokart.repository;

import com.kilokart.model.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findById(String id);

    Collection<Order> findAll();

    Collection<Order> findByCustomerId(String customerId);
}
