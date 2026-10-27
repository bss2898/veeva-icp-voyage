package com.kilokart.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String id;
    private String name;
    private String description;
    private String companyName;
    private BigDecimal price;
    private int stockQuantity;

    public Product(String id, String name, String description, BigDecimal price, int stockQuantity) {
        this(id, name, description, "Unbranded", price, stockQuantity);
    }
    //if not provided brand then it takes the default value as "Unbranded" and use other constructor to initialize the product object with the provided values.
    public Product(String id, String name, String description, String companyName,
                   BigDecimal price, int stockQuantity) {
        this.id = requireText(id, "id");
        validateDetails(name, description, companyName, price, stockQuantity);
        this.name = name.trim();
        this.description = description;
        this.companyName = companyName.trim();
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void updateDetails(String name, String description, String companyName,
                              BigDecimal price, int stockQuantity) {
        validateDetails(name, description, companyName, price, stockQuantity);
        this.name = requireText(name, "name");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.companyName = requireText(companyName, "companyName");
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    private static void validateDetails(String name, String description, String companyName,
                                        BigDecimal price, int stockQuantity) {
        requireText(name, "name");
        Objects.requireNonNull(description, "description must not be null");
        requireText(companyName, "companyName");
        Objects.requireNonNull(price, "price must not be null");
        if (price.signum() <= 0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("stockQuantity must not be negative");
        }
    }

    public void changePrice(BigDecimal price) {
        Objects.requireNonNull(price, "price must not be null");
        if (price.signum() <= 0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("stockQuantity must not be negative");
        }
        this.stockQuantity = stockQuantity;
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        if (quantity > stockQuantity) {
            throw new IllegalStateException("Insufficient stock for product " + id);
        }
        stockQuantity -= quantity;
    }

    public void restock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        stockQuantity = Math.addExact(stockQuantity, quantity);
    }
}
