package com.kilokart.app;

import com.kilokart.model.Product;
import com.kilokart.repository.InMemoryCustomerRepository;
import com.kilokart.repository.InMemoryOrderRepository;
import com.kilokart.repository.InMemoryProductRepository;
import com.kilokart.repository.OrderRepository;
import com.kilokart.service.AdministratorService;
import com.kilokart.service.CustomerService;
import com.kilokart.service.DiscountService;
import com.kilokart.service.OrderService;
import com.kilokart.service.ProductService;
import com.kilokart.service.SubscriptionService;
import com.kilokart.shipping.StandardShippingPolicy;

import java.math.BigDecimal;

public class KiloKartApplication {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService(new InMemoryCustomerRepository());
        ProductService productService = new ProductService(new InMemoryProductRepository());
        OrderRepository orderRepository = new InMemoryOrderRepository();
        DiscountService discountService = new DiscountService(
                orderRepository, 3, new BigDecimal("10"), "LOYAL10");
        OrderService orderService = new OrderService(
                productService,
                orderRepository,
                new StandardShippingPolicy(new BigDecimal("50.00"), new BigDecimal("500.00")),
                discountService
        );
        AdministratorService administratorService = new AdministratorService();
        SubscriptionService subscriptionService = new SubscriptionService();

        seedProducts(productService);
        new TerminalMenu(customerService, productService, orderService,
                administratorService, subscriptionService).run();
    }

    private static void seedProducts(ProductService products) {
        products.addProduct(new Product("P1001", "Basmati Rice", "Premium long-grain rice",
                "KiloKart Select", new BigDecimal("120.00"), 30));
        products.addProduct(new Product("P1002", "Toor Dal", "Split pigeon peas",
                "Farm Fresh", new BigDecimal("95.00"), 25));
        products.addProduct(new Product("P1003", "Olive Oil", "Extra virgin cooking oil",
                "Oliva", new BigDecimal("450.00"), 12));
        products.addProduct(new Product("P1004", "Green Tea", "Organic green tea bags",
                "Leaf & Cup", new BigDecimal("180.00"), 18));
    }
}
