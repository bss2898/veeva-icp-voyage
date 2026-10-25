package com.kilokart.repository;

import com.kilokart.model.Customer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new LinkedHashMap<>();

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Collection<Customer> findAll() {
        return java.util.List.copyOf(customers.values());
    }

    @Override
    public boolean deleteById(String id) {
        return customers.remove(id) != null;
    }
}
