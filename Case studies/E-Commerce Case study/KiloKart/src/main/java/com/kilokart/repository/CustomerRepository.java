package com.kilokart.repository;

import com.kilokart.model.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);

    Optional<Customer> findById(String id);

    Optional<Customer> findByEmail(String email);

    Collection<Customer> findAll();

    boolean deleteById(String id);
}
