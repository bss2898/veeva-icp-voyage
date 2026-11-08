package com.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InventoryManagement {
    private Inventory inventory=new Inventory();
    private Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        InventoryManagement management=new InventoryManagement();
        management.start();
    }

    public void displayMenu() {
        System.out.println("\n========== INVENTORY MANAGEMENT ==========");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Check Item Quantity");
        System.out.println("4. Display All Items");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void start() {
        boolean running=true;

        while(running) {
            displayMenu();
            int choice=readInt();

            try {
                switch(choice) {
                    case 1 -> addProduct();
                    case 2 -> removeProduct();
                    case 3 -> checkProduct();
                    case 4 -> inventory.displayInventory();
                    case 5 -> {
                        running=false;
                        System.out.println("Exiting Inventory Management System...");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch(IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Error: "+e.getMessage());
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter Product ID: ");
        String id=sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name=sc.nextLine();

        System.out.print("Enter Company Name: ");
        String company=sc.nextLine();

        System.out.print("Enter Product Price: ");
        BigDecimal price=new BigDecimal(sc.nextLine());

        System.out.print("Enter Manufacturing Date (YYYY-MM-DD): ");
        LocalDate date=LocalDate.parse(sc.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity=readInt();

        Product product=new Product(id,name,price,date,company);

        inventory.addItem(product,quantity);
    }

    private void removeProduct() {
        System.out.print("Enter Product ID: ");
        String id=sc.nextLine();

        System.out.print("Enter Quantity to Remove: ");
        int quantity=readInt();

        inventory.removeItem(id,quantity);
    }

    private void checkProduct() {
        System.out.print("Enter Product ID: ");
        String id=sc.nextLine();

        int quantity=inventory.checkQuantity(id);

        System.out.println("Available Quantity: "+quantity);
    }

    private int readInt() {
        while(true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }
}