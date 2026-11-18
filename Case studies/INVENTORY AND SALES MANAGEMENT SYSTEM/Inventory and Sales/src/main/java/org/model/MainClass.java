
package org.model;

import java.util.Scanner;
import java.math.BigDecimal;
import org.model.Company.Location;
import java.util.HashMap;

public class MainClass {

    private Inventory inventory=new Inventory();
    private Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        MainClass app=new MainClass();
        app.start();
    }

    public void displayMenu() {
        System.out.println("\n======================================");
        System.out.println(" INVENTORY AND SALES MANAGEMENT");
        System.out.println("======================================");
        System.out.println("1. Add Company");
        System.out.println("2. Update Company");
        System.out.println("3. Add Product");
        System.out.println("4. Update Product");
        System.out.println("5. Product Price Lookup");
        System.out.println("6. Update Stock");
        System.out.println("7. Process Sale");
        System.out.println("8. Display Transaction History");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    public void start() {

        boolean running=true;

        while(running) {

            displayMenu();

            try {
                int choice=Integer.parseInt(sc.nextLine());

                switch(choice) {

                    case 1 -> addCompany();

                    case 2 -> updateCompany();

                    case 3 -> addProduct();

                    case 4 -> updateProduct();

                    case 5 -> priceLookup();

                    case 6 -> updateStock();

                    case 7 -> processSale();

                    case 8 -> displayTransactions();

                    case 9 -> {
                        running=false;
                        System.out.println("Thank you for using the application!");
                    }

                    default -> System.out.println("Invalid choice!");
                }

            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    public void addCompany() {
        System.out.print("Enter company ID: ");
        String id=sc.nextLine();

        System.out.print("Enter company name: ");
        String name=sc.nextLine();

        Location[] locations=Location.values();

        for(int i=0;i<locations.length;i++) {
            System.out.println((i+1)+". "+locations[i]);
        }

        System.out.print("Choose location: ");
        int choice=Integer.parseInt(sc.nextLine());

        if(choice<1||choice>locations.length) {
            System.out.println("Invalid location!");
            return;
        }

        Location location=locations[choice-1];

        System.out.print("Enter contact details: ");
        String address=sc.nextLine();

        int companyId=Integer.parseInt(id);

        Company company=new Company(companyId,name,location,address);

        if(inventory.addCompany(id,company)) {
            System.out.println("Company added successfully!");
        } else {
            System.out.println("Company ID already exists!");
        }
    }


    public void updateCompany() {
        System.out.print("Enter company ID: ");
        String id=sc.nextLine();

        if(!inventory.companyExists(id)) {
            System.out.println("Company not found!");
            return;
        }

        System.out.print("Enter new company name: ");
        String name=sc.nextLine();

        Location[] locations=Location.values();

        for(int i=0;i<locations.length;i++) {
            System.out.println((i+1)+". "+locations[i]);
        }

        System.out.print("Choose new location: ");
        int choice=Integer.parseInt(sc.nextLine());

        if(choice<1||choice>locations.length) {
            System.out.println("Invalid location!");
            return;
        }

        Location location=locations[choice-1];

        System.out.print("Enter new contact details: ");
        String address=sc.nextLine();

        Company company=new Company(Integer.parseInt(id),name,location,address);

        inventory.updateCompanyDetails(id,company);

        System.out.println("Company updated successfully!");
    }


    public void addProduct() {
        System.out.print("Enter product ID: ");
        int id=Integer.parseInt(sc.nextLine());

        System.out.print("Enter product name: ");
        String name=sc.nextLine();

        System.out.print("Enter unit price: ");
        BigDecimal price=new BigDecimal(sc.nextLine());

        Product.Category[] categories=Product.Category.values();

        for(int i=0;i<categories.length;i++) {
            System.out.println((i+1)+". "+categories[i]);
        }

        System.out.print("Choose category: ");
        int choice=Integer.parseInt(sc.nextLine());

        if(choice<1||choice>categories.length) {
            System.out.println("Invalid category!");
            return;
        }

        Product.Category category=categories[choice-1];

        System.out.print("Enter company ID: ");
        int companyId=Integer.parseInt(sc.nextLine());

        System.out.print("Enter initial stock: ");
        int quantity=Integer.parseInt(sc.nextLine());

        Product product=new Product(id,name,price,category,companyId);

        if(inventory.addProduct(id+"",name,companyId+"",product,quantity)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Company not found, duplicate product or invalid quantity!");
        }
    }



    public void updateProduct() {
        System.out.print("Enter product ID: ");
        String id=sc.nextLine();

        Product oldProduct=inventory.getProduct(id);

        if(oldProduct==null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter new product name: ");
        String name=sc.nextLine();

        System.out.print("Enter new price: ");
        BigDecimal price=new BigDecimal(sc.nextLine());

        Product.Category[] categories=Product.Category.values();

        for(int i=0;i<categories.length;i++) {
            System.out.println((i+1)+". "+categories[i]);
        }

        System.out.print("Choose category: ");
        int choice=Integer.parseInt(sc.nextLine());

        if(choice<1||choice>categories.length) {
            System.out.println("Invalid category!");
            return;
        }

        Product.Category category=categories[choice-1];

        String companyId=oldProduct.getCompanyId();

        Product product=new Product(
                Integer.parseInt(id),
                name,
                price,
                category,
                Integer.parseInt(companyId)
        );

        if(inventory.updateProductDetails(id,product)) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Duplicate product name!");
        }
    }


    public void priceLookup() {
        System.out.println("1. Search using Product ID");
        System.out.println("2. Search using Product Name");
        System.out.print("Enter choice: ");

        int choice=Integer.parseInt(sc.nextLine());

        Product product=null;

        if(choice==1) {
            System.out.print("Enter product ID: ");
            String id=sc.nextLine();

            product=inventory.getProduct(id);
        }
        else if(choice==2) {
            System.out.print("Enter product name: ");
            String name=sc.nextLine();

            product=inventory.getProductByName(name);
        }
        else {
            System.out.println("Invalid choice!");
            return;
        }

        if(product==null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Product: "+product.getName());
        System.out.println("Price: "+product.getUnitPrice());
        System.out.println("Category: "+product.getCategory());

        Company company=inventory.getCompany(product.getCompanyId());

        if(company!=null) {
            System.out.println("Supplier: "+company);
        }
    }

    public void updateStock() {
        System.out.print("Enter product ID: ");
        String id=sc.nextLine();

        Product product=inventory.getProduct(id);

        if(product==null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Current stock: "+inventory.getStock(id));

        System.out.print("Enter new stock quantity: ");
        int quantity=Integer.parseInt(sc.nextLine());

        if(quantity<0) {
            System.out.println("Stock cannot be negative!");
            return;
        }

        inventory.changeStock(id,quantity);

        System.out.println("Stock updated successfully!");
    }

    public void processSale() {
        System.out.print("Enter number of different products: ");
        int count=Integer.parseInt(sc.nextLine());

        if(count<=0) {
            System.out.println("Invalid order!");
            return;
        }

        HashMap<String,Integer> orderItems=new HashMap<>();

        for(int i=0;i<count;i++) {
            System.out.print("Enter product ID: ");
            String id=sc.nextLine();

            System.out.print("Enter quantity: ");
            int quantity=Integer.parseInt(sc.nextLine());

            if(quantity<=0) {
                System.out.println("Quantity must be greater than zero!");
                return;
            }

            if(inventory.getProduct(id)==null) {
                System.out.println("Product not found!");
                return;
            }

            orderItems.put(id,orderItems.getOrDefault(id,0)+quantity);
        }

        // Validate stock before making any changes
        for(String id:orderItems.keySet()) {
            int requested=orderItems.get(id);
            int available=inventory.getStock(id);

            Product product=inventory.getProduct(id);

            if(available==0) {
                System.out.println("Alert: "+product.getName()+" is out of stock!");
                return;
            }

            if(requested>available) {
                System.out.println("Transaction failed! Only "+available+
                        " units of "+product.getName()+" are available.");
                return;
            }
        }

        BigDecimal total=BigDecimal.ZERO;

        for(String id:orderItems.keySet()) {
            Product product=inventory.getProduct(id);

            BigDecimal amount=product.getUnitPrice()
                    .multiply(BigDecimal.valueOf(orderItems.get(id)));

            total=total.add(amount);
        }

        // All validations passed. Now deduct stock.
        String transactionId="T"+System.currentTimeMillis();

        for(String id:orderItems.keySet()) {
            Product product=inventory.getProduct(id);

            int quantity=orderItems.get(id);
            int remaining=inventory.getStock(id)-quantity;

            inventory.changeStock(id,remaining);

            BigDecimal amount=product.getUnitPrice()
                    .multiply(BigDecimal.valueOf(quantity));

            Transaction transaction=new Transaction(
                    transactionId,
                    product.getName(),
                    quantity,
                    amount
            );

            inventory.addTransaction(transaction);

            System.out.println(product.getName()+" remaining stock: "+remaining);
        }

        System.out.println("Transaction successful!");
        System.out.println("Transaction ID: "+transactionId);
        System.out.println("Total bill: "+total);
    }

    public void displayTransactions() {
        inventory.displayAllTransactions();
    }
}
