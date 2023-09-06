package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime date;

    private BigDecimal balance;

    public Transaction(TransactionType transactionType, BigDecimal amount, BigDecimal balance) {
        this.date = LocalDateTime.now();
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getCreatedDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s", date, transactionType, amount, balance);
    }
}
