package com.kilokart.app;

import com.kilokart.exception.KiloKartException;
import com.kilokart.model.Address;
import com.kilokart.model.Administrator;
import com.kilokart.model.CartItem;
import com.kilokart.model.Customer;
import com.kilokart.model.Order;
import com.kilokart.model.OrderStatus;
import com.kilokart.model.OrderTotals;
import com.kilokart.model.Product;
import com.kilokart.model.Subscription;
import com.kilokart.model.SubscriptionDuration;
import com.kilokart.payment.CashOnDelivery;
import com.kilokart.payment.CreditCardPayment;
import com.kilokart.payment.DebitCardPayment;
import com.kilokart.payment.GiftCardPayment;
import com.kilokart.payment.PaymentMethod;
import com.kilokart.payment.UpiPayment;
import com.kilokart.service.AdministratorService;
import com.kilokart.service.CustomerService;
import com.kilokart.service.OrderService;
import com.kilokart.service.ProductService;
import com.kilokart.service.SubscriptionService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class TerminalMenu {
    private static final BigDecimal DEMO_GIFT_CARD_BALANCE = new BigDecimal("500.00");

    private final ConsoleInput input = new ConsoleInput();
    private final CustomerService customers;
    private final ProductService products;
    private final OrderService orders;
    private final AdministratorService administrators;
    private final SubscriptionService subscriptions;
    private final Map<String, GiftCardPayment> giftCards = new HashMap<>();
    private boolean running = true;

    TerminalMenu(CustomerService customers, ProductService products, OrderService orders,
                 AdministratorService administrators, SubscriptionService subscriptions) {
        this.customers = customers;
        this.products = products;
        this.orders = orders;
        this.administrators = administrators;
        this.subscriptions = subscriptions;
    }

    void run() {
        System.out.println("====================================");
        System.out.println("       Welcome to KiloKart");
        System.out.println("   Core Java OOP Shopping Demo");
        System.out.println("====================================");
        while (running) {
            System.out.println("\n1. Browse products");
            System.out.println("2. Register as a customer");
            System.out.println("3. Customer login");
            System.out.println("4. Administrator login");
            System.out.println(administrators.isConfigured()
                    ? "5. Administrator setup is complete"
                    : "5. Set up first administrator");
            System.out.println("0. Exit");
            int choice = input.readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> browseProducts();
                    case 2 -> registerCustomer();
                    case 3 -> customerLogin();
                    case 4 -> administratorLogin();
                    case 5 -> setupAdministrator();
                    case 0 -> running = false;
                    default -> System.out.println("Choose one of the listed options.");
                }
            } catch (KiloKartException | IllegalArgumentException | IllegalStateException e) {
                System.out.println("Could not complete that action: " + e.getMessage());
            }
        }
        System.out.println("Thank you for visiting KiloKart.");
    }

    private void setupAdministrator() {
        if (administrators.isConfigured()) {
            System.out.println("The first administrator has already been created.");
            return;
        }
        System.out.println("First-run administrator setup. Choose your own credentials.");
        String name = input.readText("Name: ");
        String email = input.readText("Email: ");
        String phone = input.readText("Phone: ");
        String password = input.readPassword("Create password (minimum 6 characters): ");
        administrators.createFirstAdministrator(name, email, phone, password);
        System.out.println("Administrator account created. No password is displayed or stored as plain text.");
    }

    private void registerCustomer() {
        System.out.println("\nCustomer registration");
        String name = input.readText("Name: ");
        String email = input.readText("Email: ");
        String phone = input.readText("Phone: ");
        String password = input.readPassword("Create password (minimum 6 characters): ");
        Address address = readAddress();
        Customer customer = customers.register(name, email, phone, password, address);
        System.out.println("Registration complete. You can now log in with " + customer.getEmail() + ".");
    }

    private Address readAddress() {
        return new Address(
                input.readText("Street: "),
                input.readText("City: "),
                input.readText("State: "),
                input.readText("Postal code: "),
                input.readText("Country: ")
        );
    }

    private void customerLogin() {
        String email = input.readText("Email: ");
        String password = input.readPassword("Password: ");
        Customer customer = customers.login(email, password);
        System.out.println("Welcome, " + customer.getName() + ".");
        customerMenu(customer);
    }

    private void administratorLogin() {
        String email = input.readText("Administrator email: ");
        String password = input.readPassword("Password: ");
        Administrator administrator = administrators.login(email, password);
        System.out.println("Administrator access granted. Welcome, " + administrator.getName() + ".");
        administratorMenu();
    }

    private void browseProducts() {
        while (true) {
            System.out.println("\nProduct search");
            System.out.println("1. View all products");
            System.out.println("2. Search by product ID");
            System.out.println("3. Search by name");
            System.out.println("4. Search by name and exact price");
            System.out.println("5. Search by name and price range");
            System.out.println("6. Search by name and company");
            System.out.println("7. Search by name, company, and exact price");
            System.out.println("0. Back");
            int choice = input.readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> printProducts(products.getAllProducts());
                    case 2 -> printProduct(products.getProduct(input.readText("Product ID: ")));
                    case 3 -> printProducts(products.searchByName(input.readText("Name contains: ")));
                    case 4 -> printProducts(products.searchByNameAndExactPrice(
                            input.readText("Name contains: "), readPositiveMoney("Exact price: ")));
                    case 5 -> {
                        String name = input.readText("Name contains: ");
                        BigDecimal min = readNonNegativeMoney("Minimum price: ");
                        BigDecimal max = readNonNegativeMoney("Maximum price: ");
                        printProducts(products.searchByNameAndPriceRange(name, min, max));
                    }
                    case 6 -> printProducts(products.searchByNameAndCompany(
                            input.readText("Name contains: "), input.readText("Company contains: ")));
                    case 7 -> printProducts(products.searchByNameCompanyAndPrice(
                            input.readText("Name contains: "), input.readText("Company contains: "),
                            readPositiveMoney("Exact price: ")));
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Choose one of the listed options.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Search failed: " + e.getMessage());
            }
        }
    }

    private void customerMenu(Customer customer) {
        boolean loggedIn = true;
        while (running && loggedIn) {
            System.out.println("\nCustomer menu - " + customer.getName());
            System.out.println("1. Browse/search products");
            System.out.println("2. Manage shopping cart");
            System.out.println("3. Checkout");
            System.out.println("4. View my orders and tracking");
            System.out.println("5. Update my profile");
            System.out.println("6. Manage subscription");
            System.out.println("0. Log out");
            int choice = input.readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> browseProducts();
                    case 2 -> cartMenu(customer);
                    case 3 -> checkout(customer);
                    case 4 -> showCustomerOrders(customer);
                    case 5 -> updateCustomerProfile(customer);
                    case 6 -> subscriptionMenu(customer);
                    case 0 -> loggedIn = false;
                    default -> System.out.println("Choose one of the listed options.");
                }
            } catch (KiloKartException | IllegalArgumentException | IllegalStateException e) {
                System.out.println("Could not complete that action: " + e.getMessage());
            }
        }
        if (running) {
            System.out.println("You have been logged out.");
        }
    }

    private void cartMenu(Customer customer) {
        boolean inCartMenu = true;
        while (inCartMenu) {
            System.out.println("\nShopping cart");
            System.out.println("1. View cart");
            System.out.println("2. Add product");
            System.out.println("3. Change quantity (0 removes item)");
            System.out.println("4. Remove product");
            System.out.println("0. Back");
            int choice = input.readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> showCart(customer);
                    case 2 -> {
                        Product product = products.getProduct(input.readText("Product ID: "));
                        int quantity = input.readInt("Quantity: ");
                        customer.getShoppingCart().addProduct(product, quantity);
                        System.out.println("Product added to cart.");
                    }
                    case 3 -> {
                        String id = input.readText("Product ID: ");
                        int quantity = input.readInt("New quantity (0 removes it): ");
                        customer.getShoppingCart().changeQuantity(id, quantity);
                        System.out.println("Cart updated.");
                    }
                    case 4 -> {
                        customer.getShoppingCart().removeProduct(input.readText("Product ID: "));
                        System.out.println("If it was in the cart, it has been removed.");
                    }
                    case 0 -> inCartMenu = false;
                    default -> System.out.println("Choose one of the listed options.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Could not update the cart: " + e.getMessage());
            }
        }
    }

    private void showCart(Customer customer) {
        List<CartItem> items = customer.getShoppingCart().getItems();
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (CartItem item : items) {
            System.out.printf("%s | %s | quantity %d | subtotal %s%n",
                    item.getProduct().getId(), item.getProduct().getName(),
                    item.getQuantity(), money(item.getSubtotal()));
        }
        System.out.println("Cart subtotal: " + money(customer.getShoppingCart().getTotal()));
    }

    private void checkout(Customer customer) {
        String coupon = input.readText("Coupon code (press Enter to skip): ");
        boolean subscriber = subscriptions.hasActiveSubscription(customer.getId());
        OrderTotals totals = orders.quoteOrder(customer, coupon, subscriber);
        System.out.println("\nCheckout summary");
        System.out.println("Subtotal:     " + money(totals.getSubtotal()));
        System.out.println("Shipping:     " + money(totals.getShippingCost()));
        System.out.println("Discount:     -" + money(totals.getDiscount()));
        System.out.println("Final total:  " + money(totals.getTotal()));
        if (!input.confirm("Place this order?")) {
            System.out.println("Checkout cancelled. Your cart and stock are unchanged.");
            return;
        }
        PaymentMethod paymentMethod = choosePaymentMethod(customer);
        if (paymentMethod == null) {
            System.out.println("Checkout cancelled. Your cart and stock are unchanged.");
            return;
        }
        Order order = orders.placeOrder(customer, paymentMethod, coupon, subscriber);
        System.out.println("Order confirmed. Order ID: " + order.getId());
        System.out.println("Payment method: " + paymentMethod.getName());
    }

    private PaymentMethod choosePaymentMethod(Customer customer) {
        System.out.println("\nChoose simulated payment method");
        System.out.println("1. UPI");
        System.out.println("2. Debit card");
        System.out.println("3. Credit card");
        System.out.println("4. Cash on delivery");
        System.out.println("5. Gift card (demo balance "
                + money(giftCards.computeIfAbsent(customer.getId(),
                id -> new GiftCardPayment(DEMO_GIFT_CARD_BALANCE)).getBalance()) + ")");
        System.out.println("0. Cancel checkout");
        return switch (input.readInt("Choose an option: ")) {
            case 1 -> new UpiPayment();
            case 2 -> new DebitCardPayment();
            case 3 -> new CreditCardPayment();
            case 4 -> new CashOnDelivery();
            case 5 -> giftCards.get(customer.getId());
            case 0 -> null;
            default -> {
                System.out.println("Invalid payment choice; checkout cancelled.");
                yield null;
            }
        };
    }

    private void showCustomerOrders(Customer customer) {
        List<Order> customerOrders = orders.getOrdersForCustomer(customer.getId());
        if (customerOrders.isEmpty()) {
            System.out.println("You do not have any orders yet.");
            return;
        }
        for (Order order : customerOrders) {
            System.out.printf("%nOrder %s | %s | placed %s%n",
                    order.getId(), order.getStatus(), order.getCreatedAt());
            System.out.printf("Subtotal %s | shipping %s | discount %s | total %s%n",
                    money(order.getSubtotal()), money(order.getShippingCost()),
                    money(order.getDiscount()), money(order.getTotal()));
            if (order.getTrackingInformation() != null) {
                System.out.println("Tracking: " + order.getTrackingInformation());
            }
            order.getItems().forEach(item -> System.out.printf("  %s x%d%n",
                    item.getProductName(), item.getQuantity()));
        }
    }

    private void updateCustomerProfile(Customer customer) {
        System.out.println("Update your profile. Password is not changed here.");
        String name = input.readText("Name: ");
        String email = input.readText("Email: ");
        String phone = input.readText("Phone: ");
        Address address = readAddress();
        customers.updateProfile(customer, name, email, phone, address);
        System.out.println("Profile updated.");
    }

    private void subscriptionMenu(Customer customer) {
        Subscription subscription = subscriptions.getSubscription(customer.getId()).orElse(null);
        if (subscription != null) {
            System.out.printf("Subscription: %s, %s, ends %s%n",
                    subscription.getDuration(), subscription.getStatus(), subscription.getEndDate());
        }
        if (subscriptions.hasActiveSubscription(customer.getId())) {
            System.out.println("Your active subscription already gives you free shipping.");
            return;
        }
        System.out.println("1. Monthly");
        System.out.println("2. Quarterly");
        System.out.println("3. Half-yearly");
        System.out.println("4. Yearly");
        System.out.println("0. Back");
        int choice = input.readInt("Choose a subscription duration: ");
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > SubscriptionDuration.values().length) {
            System.out.println("Choose one of the listed options.");
            return;
        }
        SubscriptionDuration duration = SubscriptionDuration.values()[choice - 1];
        if (input.confirm("Activate the " + duration + " subscription? This is a local demo with no charge")) {
            Subscription created = subscriptions.subscribe(customer.getId(), duration);
            System.out.println("Subscription active until " + created.getEndDate()
                    + ". You now receive free shipping.");
        }
    }

    private void administratorMenu() {
        boolean loggedIn = true;
        while (running && loggedIn) {
            System.out.println("\nAdministrator menu");
            System.out.println("1. View products");
            System.out.println("2. Add product");
            System.out.println("3. Update product");
            System.out.println("4. Remove product");
            System.out.println("5. Search customers");
            System.out.println("6. Remove customer");
            System.out.println("7. View/manage orders");
            System.out.println("0. Log out");
            int choice = input.readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> printProducts(products.getAllProducts());
                    case 2 -> addProduct();
                    case 3 -> updateProduct();
                    case 4 -> removeProduct();
                    case 5 -> searchCustomers();
                    case 6 -> removeCustomer();
                    case 7 -> manageOrders();
                    case 0 -> loggedIn = false;
                    default -> System.out.println("Choose one of the listed options.");
                }
            } catch (KiloKartException | IllegalArgumentException | IllegalStateException e) {
                System.out.println("Could not complete that action: " + e.getMessage());
            }
        }
        if (running) {
            System.out.println("Administrator logged out.");
        }
    }

    private void addProduct() {
        String id = input.readText("Product ID: ");
        String name = input.readText("Name: ");
        String description = input.readText("Description: ");
        String company = input.readText("Company: ");
        BigDecimal price = readPositiveMoney("Price: ");
        int stock = readNonNegativeInt("Stock quantity: ");
        products.addProduct(new Product(id, name, description, company, price, stock));
        System.out.println("Product added.");
    }

    private void updateProduct() {
        String id = input.readText("Product ID to update: ");
        Product existing = products.getProduct(id);
        System.out.printf("Current: %s | %s | %s | %s | stock %d%n",
                existing.getName(), existing.getDescription(), existing.getCompanyName(),
                money(existing.getPrice()), existing.getStockQuantity());
        products.updateProduct(id,
                input.readText("New name: "),
                input.readText("New description: "),
                input.readText("New company: "),
                readPositiveMoney("New price: "),
                readNonNegativeInt("New stock: "));
        System.out.println("Product updated.");
    }

    private void removeProduct() {
        String id = input.readText("Product ID to remove: ");
        if (!products.removeProduct(id)) {
            System.out.println("No product found with that ID.");
        } else {
            System.out.println("Product removed from the catalog.");
        }
    }

    private void searchCustomers() {
        System.out.println("Search customers: 1. Name  2. City  3. Phone  4. Email");
        List<Customer> results = switch (input.readInt("Choose a search type: ")) {
            case 1 -> customers.searchByName(input.readText("Name contains: "));
            case 2 -> customers.searchByCity(input.readText("City: "));
            case 3 -> customers.searchByPhone(input.readText("Phone: "));
            case 4 -> customers.searchByEmail(input.readText("Email: "));
            default -> {
                System.out.println("Invalid search type.");
                yield List.of();
            }
        };
        if (results.isEmpty()) {
            System.out.println("No matching customers found.");
            return;
        }
        for (Customer customer : results) {
            System.out.printf("ID %s | %s | %s | city %s%n",
                    customer.getId(), customer.getName(), customer.getEmail(),
                    customer.getAddress().getCity());
        }
    }

    private void removeCustomer() {
        String id = input.readText("Customer ID to remove: ");
        if (!customers.removeCustomer(id)) {
            System.out.println("No customer found with that ID.");
        } else {
            System.out.println("Customer account removed.");
        }
    }

    private void manageOrders() {
        List<Order> allOrders = orders.getAllOrders();
        if (allOrders.isEmpty()) {
            System.out.println("There are no orders.");
            return;
        }
        allOrders.forEach(order -> System.out.printf("%s | customer %s | %s | total %s%n",
                order.getId(), order.getCustomerId(), order.getStatus(), money(order.getTotal())));
        if (!input.confirm("Update an order status?")) {
            return;
        }
        String orderId = input.readText("Order ID: ");
        Order order = orders.getOrder(orderId);
        System.out.println("Current status: " + order.getStatus());
        OrderStatus[] statuses = OrderStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, statuses[i]);
        }
        int choice = input.readInt("New status: ");
        if (choice < 1 || choice > statuses.length) {
            System.out.println("Invalid order status.");
            return;
        }
        OrderStatus nextStatus = statuses[choice - 1];
        String tracking = null;
        if (nextStatus == OrderStatus.DISPATCHED || nextStatus == OrderStatus.IN_TRANSIT) {
            tracking = input.readText("Tracking information (blank to leave unchanged): ");
        }
        orders.updateOrderStatus(orderId, nextStatus, tracking);
        System.out.println("Order status updated to " + nextStatus + ".");
    }

    private void printProducts(Collection<Product> result) {
        if (result.isEmpty()) {
            System.out.println("No matching products.");
            return;
        }
        result.forEach(this::printProduct);
    }

    private void printProduct(Product product) {
        System.out.printf("%s | %s | %s | %s | %s | stock %d%n",
                product.getId(), product.getName(), product.getDescription(),
                product.getCompanyName(), money(product.getPrice()), product.getStockQuantity());
    }

    private BigDecimal readPositiveMoney(String prompt) {
        while (true) {
            BigDecimal amount = input.readMoney(prompt);
            if (amount.signum() > 0) {
                return amount;
            }
            System.out.println("Amount must be greater than zero.");
        }
    }

    private BigDecimal readNonNegativeMoney(String prompt) {
        while (true) {
            BigDecimal amount = input.readMoney(prompt);
            if (amount.signum() >= 0) {
                return amount;
            }
            System.out.println("Amount must not be negative.");
        }
    }

    private int readNonNegativeInt(String prompt) {
        while (true) {
            int value = input.readInt(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("Value must not be negative.");
        }
    }

    private String money(BigDecimal amount) {
        return "INR " + amount.setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
