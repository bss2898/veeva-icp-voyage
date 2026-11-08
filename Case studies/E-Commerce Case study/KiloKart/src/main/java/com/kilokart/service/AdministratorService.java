package com.kilokart.service;

import com.kilokart.exception.AuthenticationException;
import com.kilokart.model.Administrator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Locale;

public class AdministratorService {
    private final Map<String, Administrator> administrators = new LinkedHashMap<>();

    public Administrator createFirstAdministrator(String name, String email, String phone, String password) {
        if (!administrators.isEmpty()) {
            throw new IllegalStateException("An administrator has already been configured");
        }
        Administrator administrator = new Administrator(UUID.randomUUID().toString(),
                name, email, phone, password);
        administrators.put(administrator.getEmail(), administrator);
        return administrator;
    }

    public Administrator login(String email, String password) {
        Administrator administrator = email == null
                ? null : administrators.get(email.toLowerCase(Locale.ROOT));
        if (administrator == null || !administrator.authenticate(password)) {
            throw new AuthenticationException("Invalid administrator email or password");
        }
        return administrator;
    }

    public boolean isConfigured() {
        return !administrators.isEmpty();
    }
}
