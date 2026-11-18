
package org.model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private BigDecimal unitPrice;
    private Category category;
    private int companyId;

    public enum Category {
        Electronic, Grocery, Fashion, Snacks, other
    }

    public Product(int id,String name,BigDecimal unitPrice,Category category,int companyId) {
        this.id=id;
        this.name=name;
        this.unitPrice=unitPrice;
        this.category=category;
        this.companyId=companyId;
    }

    // In case no category is given
    public Product(int id,String name,BigDecimal unitPrice,int companyId) {
        this(id,name,unitPrice,Category.other,companyId);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getCompanyId() {
        return companyId+"";
    }

    public void updatePrice(BigDecimal unitPrice) {
        if(unitPrice==null||unitPrice.compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.unitPrice=unitPrice;
    }
}
