
package org.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String productName;
    private int quantity;
    private BigDecimal totalAmount;
    private LocalDateTime timestamp;

    public Transaction(String transactionId,String productName,int quantity,BigDecimal totalAmount) {
        this.transactionId=transactionId;
        this.productName=productName;
        this.quantity=quantity;
        this.totalAmount=totalAmount;
        this.timestamp=LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
