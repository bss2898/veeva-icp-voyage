package com.kilokart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new NullPointerException("product must not be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        CartItem existingItem = items.get(product.getId());
        int updatedQuantity = Math.addExact(
                quantity, existingItem == null ? 0 : existingItem.getQuantity());
        if (updatedQuantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("quantity exceeds available stock");
        }
        if (existingItem == null) {
            items.put(product.getId(), new CartItem(product, quantity));
        } else {
            existingItem.setQuantity(updatedQuantity);
        }
    }

    public void removeProduct(String productId) {
        items.remove(productId);
    }

    public void changeQuantity(String productId, int quantity) {
        CartItem item = items.get(productId);
        if (item == null) {
            throw new IllegalArgumentException("Product is not in the cart: " + productId);
        }
        if (quantity == 0) {
            items.remove(productId);
        } else {
            item.setQuantity(quantity);
        }
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(new ArrayList<>(items.values()));
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
