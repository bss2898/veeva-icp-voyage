package com.kilokart.service;

import com.kilokart.exception.InsufficientStockException;
import com.kilokart.exception.PaymentFailedException;
import com.kilokart.model.CartItem;
import com.kilokart.model.Customer;
import com.kilokart.model.Order;
import com.kilokart.model.OrderItem;
import com.kilokart.model.OrderStatus;
import com.kilokart.model.OrderTotals;
import com.kilokart.model.Product;
import com.kilokart.payment.PaymentMethod;
import com.kilokart.repository.InMemoryOrderRepository;
import com.kilokart.repository.OrderRepository;
import com.kilokart.shipping.ShippingPolicy;
import com.kilokart.shipping.StandardShippingPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderService {
    private final ProductService productService;
    private final OrderRepository orders;
    private final ShippingPolicy shippingPolicy;
    private final DiscountService discountService;

    public OrderService(ProductService productService) {
        this(productService, new InMemoryOrderRepository(),
                new StandardShippingPolicy(new BigDecimal("50.00"), new BigDecimal("500.00")), null);
    }

    public OrderService(ProductService productService, OrderRepository orders,
                        ShippingPolicy shippingPolicy, DiscountService discountService) {
        this.productService = Objects.requireNonNull(productService, "productService must not be null");
        this.orders = Objects.requireNonNull(orders, "orders must not be null");
        this.shippingPolicy = Objects.requireNonNull(shippingPolicy, "shippingPolicy must not be null");
        this.discountService = discountService;
    }

    public OrderTotals quoteOrder(Customer customer, String couponCode, boolean subscriber) {
        Objects.requireNonNull(customer, "customer must not be null");
        List<CartItem> cartItems = customer.getShoppingCart().getItems();
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot check out with an empty cart");
        }
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            Product product = productService.getProduct(item.getProduct().getId());
            if (item.getQuantity() > product.getStockQuantity()) {
                throw new InsufficientStockException("Insufficient stock for " + product.getName());
            }
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        BigDecimal shipping = shippingPolicy.calculate(subtotal, subscriber);
        BigDecimal discount = discountService == null
                ? BigDecimal.ZERO
                : discountService.calculateDiscount(customer, subtotal, couponCode);
        return new OrderTotals(subtotal, shipping, discount);
    }

    public Order placeOrder(Customer customer, PaymentMethod paymentMethod) {
        return placeOrder(customer, paymentMethod, null, false);
    }

    public Order placeOrder(Customer customer, PaymentMethod paymentMethod,
                            String couponCode, boolean subscriber) {
        Objects.requireNonNull(customer, "customer must not be null");
        Objects.requireNonNull(paymentMethod, "paymentMethod must not be null");
        OrderTotals totals = quoteOrder(customer, couponCode, subscriber);

        if (!paymentMethod.pay(totals.getTotal())) {
            throw new PaymentFailedException("Payment was declined or the gift-card balance was insufficient");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : customer.getShoppingCart().getItems()) {
            Product product = productService.getProduct(cartItem.getProduct().getId());
            if (cartItem.getQuantity() > product.getStockQuantity()) {
                throw new InsufficientStockException("Insufficient stock for " + product.getName());
            }
            orderItems.add(new OrderItem(product.getId(), product.getName(), product.getPrice(),
                    cartItem.getQuantity()));
        }
        for (CartItem cartItem : customer.getShoppingCart().getItems()) {
            productService.getProduct(cartItem.getProduct().getId()).reduceStock(cartItem.getQuantity());
        }

        Order order = new Order(UUID.randomUUID().toString(), customer.getId(), orderItems,
                customer.getAddress(), totals.getSubtotal(), totals.getShippingCost(), totals.getDiscount(),
                totals.getTotal(), OrderStatus.PLACED);
        order.setStatus(OrderStatus.CONFIRMED);
        orders.save(order);
        if (discountService != null) {
            discountService.markCouponUsed(customer, couponCode);
        }
        customer.getShoppingCart().clear();
        return order;
    }

    public Order getOrder(String orderId) {
        return orders.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public List<Order> getOrdersForCustomer(String customerId) {
        return List.copyOf(orders.findByCustomerId(customerId));
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders.findAll());
    }

    public void updateOrderStatus(String orderId, OrderStatus status, String trackingInformation) {
        Order order = getOrder(orderId);
        order.setStatus(status);
        if (trackingInformation != null && !trackingInformation.isBlank()) {
            order.setTrackingInformation(trackingInformation);
        }
        orders.save(order);
    }
}
