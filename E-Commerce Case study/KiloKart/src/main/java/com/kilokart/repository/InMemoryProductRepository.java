package com.kilokart.repository;

import com.kilokart.model.Product;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> products = new LinkedHashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Collection<Product> findAll() {
        return java.util.List.copyOf(products.values());
    }

    @Override
    public boolean deleteById(String id) {
        return products.remove(id) != null;
    }
}
