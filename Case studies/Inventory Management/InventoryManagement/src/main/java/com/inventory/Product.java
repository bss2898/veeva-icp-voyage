package com.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private LocalDate dateOfManufacture;
    private int quantityInHand;
    private String company;

    public Product(String productId,String name,BigDecimal price,LocalDate dateOfManufacture,String company) {
        if(productId==null||productId.isBlank()||name==null||name.isBlank()||company==null||company.isBlank()) {
            throw new IllegalArgumentException("Product details cannot be empty");
        }

        if(price==null||price.compareTo(BigDecimal.ZERO)<=0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        if(dateOfManufacture==null||dateOfManufacture.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid manufacturing date");
        }

        this.productId=productId;
        this.name=name;
        this.price=price;
        this.dateOfManufacture=dateOfManufacture;
        this.company=company;
        this.quantityInHand=0;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture;
    }

    public String getCompany() {
        return company;
    }

    public int getQuantityInHand() {
        return quantityInHand;
    }

    public void addStock(int quantity) {
        if(quantity<=0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        quantityInHand+=quantity;
    }

    public void removeStock(int quantity) {
        if(quantity<=0||quantity>quantityInHand) {
            throw new IllegalArgumentException("Invalid quantity to remove");
        }

        quantityInHand-=quantity;
    }

    @Override
    public String toString() {
        return "Product ID: "+productId+
                "\nName: "+name+
                "\nCompany: "+company+
                "\nPrice: Rs."+price+
                "\nManufacturing Date: "+dateOfManufacture+
                "\nQuantity: "+quantityInHand;
    }
}