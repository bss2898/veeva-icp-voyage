package com.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String,Product> items=new HashMap<>();

    public void addItem(Product product,int quantity) {
        if(product==null||quantity<=0) {
            throw new IllegalArgumentException("Invalid product or quantity");
        }

        String productId=product.getProductId();

        if(items.containsKey(productId)) {
            items.get(productId).addStock(quantity);
        } else {
            product.addStock(quantity);
            items.put(productId,product);
        }

        System.out.println(quantity+" units of "+product.getName()+" added successfully");
    }

    public void removeItem(String productId,int quantity) {
        Product product=items.get(productId);

        if(product==null) {
            throw new IllegalArgumentException("Product not found");
        }

        product.removeStock(quantity);

        if(product.getQuantityInHand()==0) {
            items.remove(productId);
        }

        System.out.println(quantity+" units of "+product.getName()+" removed successfully");
    }

    public int checkQuantity(String productId) {
        Product product=items.get(productId);

        if(product==null) {
            throw new IllegalArgumentException("Product not found");
        }

        return product.getQuantityInHand();
    }

    public void displayInventory() {
        if(items.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }

        for(Product product:items.values()) {
            System.out.println("----------------------------");
            System.out.println(product);
        }

        System.out.println("----------------------------");
    }
}