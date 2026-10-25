package com.kilokart.service;

import com.kilokart.model.Customer;
import com.kilokart.exception.AuthenticationException;
import com.kilokart.repository.CustomerRepository;
import com.kilokart.repository.InMemoryCustomerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Locale;
import com.kilokart.model.Address;

public class CustomerService {
    private final CustomerRepository customers;

    public CustomerService() {
        this(new InMemoryCustomerRepository());
    }

    public CustomerService(CustomerRepository customers) {
        this.customers = Objects.requireNonNull(customers, "customers must not be null");
    }

    public void addCustomer(Customer customer) {
        Objects.requireNonNull(customer, "customer must not be null");
        if (customers.findById(customer.getId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID already exists: " + customer.getId());
        }
        if (customers.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Customer email already exists: " + customer.getEmail());
        }
        customers.save(customer);
    }

    public Customer register(String name, String email, String phone, String password,
                             Address address) {
        validateEmail(email);
        Customer customer = new Customer(UUID.randomUUID().toString(), name, email, phone, address, password);
        addCustomer(customer);
        return customer;
    }

    public Customer login(String email, String password) {
        if (email == null) {
            throw new AuthenticationException("Invalid email or password");
        }
        Customer customer = customers.findByEmail(email).orElse(null);
        if (customer == null || !customer.authenticate(password)) {
            throw new AuthenticationException("Invalid email or password");
        }
        return customer;
    }

    public Customer getCustomer(String customerId) {
        return customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }

    public Collection<Customer> getAllCustomers() {
        return customers.findAll();
    }

    public boolean removeCustomer(String customerId) {
        return customers.deleteById(customerId);
    }

    public void updateProfile(Customer customer, String name, String email, String phone, Address address) {
        Objects.requireNonNull(customer, "customer must not be null");
        validateEmail(email);
        Customer existing = customers.findByEmail(email).orElse(null);
        if (existing != null && !existing.getId().equals(customer.getId())) {
            throw new IllegalArgumentException("That email address is already registered");
        }
        customer.updateProfile(name, email, phone, address);
    }

    public List<Customer> searchByName(String name) {
        return customers.findAll().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Customer> searchByCity(String city) {
        return customers.findAll().stream()
                .filter(customer -> customer.getAddress().getCity().equalsIgnoreCase(city))
                .toList();
    }

    public List<Customer> searchByPhone(String phone) {
        return customers.findAll().stream()
                .filter(customer -> customer.getPhone().equals(phone))
                .toList();
    }

    public List<Customer> searchByEmail(String email) {
        return customers.findByEmail(email).map(List::of).orElseGet(List::of);
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Enter a valid email address");
        }
    }
}
