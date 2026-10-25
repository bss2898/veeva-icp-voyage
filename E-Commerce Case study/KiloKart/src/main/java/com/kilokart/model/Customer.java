package com.kilokart.model;

import java.util.Objects;

public class Customer extends User {
    private Address address;
    private final ShoppingCart shoppingCart = new ShoppingCart();

    public Customer(String id, String name, String email, String phone, Address address, String password) {
        super(id, name, email, phone, password);
        this.address = Objects.requireNonNull(address, "address must not be null");
    }

    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }

    public Address getAddress() {
        return address;
    }

    public void updateProfile(String name, String email, String phone, Address address) {
        updateContactDetails(name, email, phone);
        this.address = Objects.requireNonNull(address, "address must not be null");
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
