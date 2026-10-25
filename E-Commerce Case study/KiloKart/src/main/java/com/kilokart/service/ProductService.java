package com.kilokart.service;

import com.kilokart.model.Product;
import com.kilokart.repository.InMemoryProductRepository;
import com.kilokart.repository.ProductRepository;

import java.util.Collection;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductService {
    private final ProductRepository products;

    public ProductService() {
        this(new InMemoryProductRepository());
    }

    public ProductService(ProductRepository products) {
        this.products = Objects.requireNonNull(products, "products must not be null");
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "product must not be null");
        if (products.findById(product.getId()).isPresent()) {
            throw new IllegalArgumentException("Product already exists: " + product.getId());
        }
        products.save(product);
    }

    public Product getProduct(String productId) {
        return products.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
    }

    public Collection<Product> getAllProducts() {
        return products.findAll();
    }

    public void restockProduct(String productId, int quantity) {
        getProduct(productId).restock(quantity);
    }

    public boolean removeProduct(String productId) {
        return products.deleteById(productId);
    }

    public void updateProduct(String id, String name, String description, String company,
                              BigDecimal price, int stock) {
        getProduct(id).updateDetails(name, description, company, price, stock);
    }

    public List<Product> searchByName(String name) {
        return products.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Product> searchByNameAndExactPrice(String name, BigDecimal price) {
        return products.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())
                        && product.getPrice().compareTo(price) == 0)
                .toList();
    }

    public List<Product> searchByNameAndPriceRange(String name, BigDecimal minimum, BigDecimal maximum) {
        if (minimum.compareTo(maximum) > 0) {
            throw new IllegalArgumentException("Minimum price must not exceed maximum price");
        }
        return products.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())
                        && product.getPrice().compareTo(minimum) >= 0
                        && product.getPrice().compareTo(maximum) <= 0)
                .toList();
    }

    public List<Product> searchByNameAndCompany(String name, String company) {
        return products.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())
                        && product.getCompanyName().toLowerCase().contains(company.toLowerCase()))
                .toList();
    }

    public List<Product> searchByNameCompanyAndPrice(String name, String company, BigDecimal price) {
        return searchByNameAndCompany(name, company).stream()
                .filter(product -> product.getPrice().compareTo(price) == 0)
                .toList();
    }
}
