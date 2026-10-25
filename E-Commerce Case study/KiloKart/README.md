# KiloKart

KiloKart is a Core Java, in-memory shopping application that demonstrates OOP concepts and a terminal-based customer/admin workflow. It does not use a database, web framework, external library, or real payment provider. Data is reset when the application exits.

## Run from Windows Command Prompt

Use JDK 22 and run these commands from the project root:

```cmd
if not exist out mkdir out
javac -d out -sourcepath src\main\java src\main\java\com\kilokart\app\KiloKartApplication.java
java -cp out com.kilokart.app.KiloKartApplication
```

You can also open `KiloKartApplication.java` in an IDE and run its `main` method.

## First run

Choose **Set up first administrator** and create your own administrator credentials. No administrator password is embedded in the source. Customer registration and login are separate. Passwords are salted and hashed in memory and are never printed by the application.

Sample products are loaded at startup. Customers can browse without an account, but must register and log in to manage a cart, subscribe, or place an order. Administrators have separate catalog, customer, and order-management menus.

## Demo policies

- Shipping costs INR 50 for orders below INR 500. Orders of INR 500 or more ship free.
- Active subscriptions receive free shipping. Subscription activation is simulated and does not charge a fee.
- Coupon `LOYAL10` gives 10% off after three delivered orders and can be used once per customer during an application run.
- Each customer gets a simulated INR 500 gift-card balance on first use during the current run.
- UPI, debit card, credit card, and cash-on-delivery payments are simulated locally. Do not enter real banking or card credentials.

Change the shipping fee/threshold and coupon settings in `KiloKartApplication` when experimenting with the policies.

## Package layout

- `app`: application wiring and terminal menus
- `model`: customers, administrators, products, carts, orders, statuses, and subscriptions
- `service`: registration, catalog, order, discount, and subscription business logic
- `repository`: in-memory storage contracts and implementations
- `payment`: simulated payment strategies
- `shipping`: shipping policy
- `exception`: business and authentication exceptions
