
package org.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private HashMap<String,Company> companyHashMap=new HashMap<>();

    private HashMap<String,Product> productHashMap=new HashMap<>();

    private HashMap<String,String> productNameIndex=new HashMap<>();

    private HashMap<String,Integer> inventoryHashMap=new HashMap<>();

    private ArrayList<Transaction> transactionList=new ArrayList<>();

    // Add company
    public boolean addCompany(String id,Company company) {

        if(companyHashMap.containsKey(id)) {
            return false;
        }

        companyHashMap.put(id,company);
        return true;
    }

    // Add product with initial stock
    public boolean addProduct(String id,String name,String companyId,Product product,int quantity) {

        if(!companyHashMap.containsKey(companyId)) {
            return false;
        }

        String key=name.trim().toLowerCase();

        if(productHashMap.containsKey(id)||productNameIndex.containsKey(key)) {
            return false;
        }

        if(quantity<0) {
            return false;
        }

        productHashMap.put(id,product);
        productNameIndex.put(key,id);
        inventoryHashMap.put(id,quantity);

        return true;
    }

    // Store completed transaction
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    // Other methods - implement these yourself

    public void updateCompany() {

    }

    public void updateProduct() {

    }

    public void updateStock() {

    }

    public void findProductById() {

    }

    public void findProductByName() {

    }

    public void displayProductPrice() {

    }

    public void processSale() {

    }

    public void displayTransactions() {

    }

    public boolean companyExists(String id) {
        return companyHashMap.containsKey(id);
    }

    public Company getCompany(String id) {
        return companyHashMap.get(id);
    }

    public Product getProduct(String id) {
        return productHashMap.get(id);
    }

    public Product getProductByName(String name) {
        String id=productNameIndex.get(name.trim().toLowerCase());

        if(id==null) {
            return null;
        }

        return productHashMap.get(id);
    }

    public String getProductIdByName(String name) {
        return productNameIndex.get(name.trim().toLowerCase());
    }

    public int getStock(String id) {
        return inventoryHashMap.getOrDefault(id,0);
    }

    public boolean changeStock(String id,int quantity) {
        if(!inventoryHashMap.containsKey(id)||quantity<0) {
            return false;
        }

        inventoryHashMap.put(id,quantity);
        return true;
    }

    public boolean updateCompanyDetails(String id,Company company) {
        if(!companyHashMap.containsKey(id)) {
            return false;
        }

        companyHashMap.put(id,company);
        return true;
    }

    public boolean updateProductDetails(String id,Product product) {
        if(!productHashMap.containsKey(id)) {
            return false;
        }

        productHashMap.put(id,product);
        return true;
    }

    public void displayAllTransactions() {
        for(Transaction transaction:transactionList) {
            System.out.println("Transaction ID: "+transaction.getTransactionId());
            System.out.println("Product: "+transaction.getProductName());
            System.out.println("Quantity: "+transaction.getQuantity());
            System.out.println("Total: "+transaction.getTotalAmount());
            System.out.println("Time: "+transaction.getTimestamp());
            System.out.println("-----------------------------");
        }
    }

}
