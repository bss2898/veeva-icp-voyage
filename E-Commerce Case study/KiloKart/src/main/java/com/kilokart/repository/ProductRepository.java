package com.kilokart.repository;

import com.kilokart.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);

    Optional<Product> findById(String id);

    Collection<Product> findAll();

    boolean deleteById(String id);
}
