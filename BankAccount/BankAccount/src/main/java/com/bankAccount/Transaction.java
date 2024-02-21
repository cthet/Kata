package main.java.com.bankAccount;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private Operation operation;
    private Date createdAt;
    private BigDecimal amount;

    public Transaction(Operation operation, BigDecimal amount, DateTimeProvider dateTimeProvider) {
        this.createdAt = dateTimeProvider.now();
        this.operation = operation;
        this.amount = amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public Date getDate() {
        return createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
