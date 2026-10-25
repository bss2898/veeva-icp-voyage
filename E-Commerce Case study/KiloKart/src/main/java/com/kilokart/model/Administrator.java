package com.kilokart.model;

public class Administrator extends User {
    public Administrator(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public Role getRole() {
        return Role.ADMINISTRATOR;
    }
}
