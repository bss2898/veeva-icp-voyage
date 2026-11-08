package com.kilokart.model;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class User {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PASSWORD_ITERATIONS = 120_000;
    private static final int PASSWORD_KEY_LENGTH = 256;

    private final String id;
    private String name;
    private String email;
    private String phone;
    private final byte[] passwordSalt = new byte[16];
    private byte[] passwordHash;

    protected User(String id, String name, String email, String phone, String password) {
        this.id = required(id, "id");
        updateContactDetails(name, email, phone);
        setPassword(password);
    }

    public final String getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getEmail() {
        return email;
    }

    public final String getPhone() {
        return phone;
    }

    public abstract Role getRole();

    public final void updateContactDetails(String name, String email, String phone) {
        this.name = required(name, "name");
        this.email = requiredEmail(email);
        this.phone = required(phone, "phone");
    }

    public final void setPassword(String password) {
        Objects.requireNonNull(password, "password must not be null");
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must contain at least 6 characters");
        }
        RANDOM.nextBytes(passwordSalt);
        passwordHash = hash(passwordSalt, password.toCharArray());
    }

    public final boolean authenticate(String password) {
        if (password == null) {
            return false;
        }
        return MessageDigest.isEqual(passwordHash, hash(passwordSalt, password.toCharArray()));
    }

    private static byte[] hash(byte[] salt, char[] password) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, PASSWORD_ITERATIONS, PASSWORD_KEY_LENGTH);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
        } catch (java.security.GeneralSecurityException e) {
            throw new IllegalStateException("Password hashing is unavailable", e);
        } finally {
            spec.clearPassword();
            java.util.Arrays.fill(password, '\0');
        }
    }

    private static String required(String value, String field) {
        Objects.requireNonNull(value, field + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }

    private static String requiredEmail(String email) {
        String value = required(email, "email");
        if (!value.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Enter a valid email address");
        }
        return value.toLowerCase(Locale.ROOT);
    }
}
