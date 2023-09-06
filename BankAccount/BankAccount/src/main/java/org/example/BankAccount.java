package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BankAccount {
    private BigDecimal balance;
    private ArrayList<Transaction> transactions;

    public BankAccount() {
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("deposit amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("deposit must be positive");
        }
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, balance));
    }


    public void withdraw(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("withdraw amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("withdraw amount must be positive");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("insufficient balance");
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount.negate(), balance));
    }


    public void printStatement() {
        System.out.println("Date\tType\tAmount\tBalance");
        transactions.stream()
                .map(Transaction::toString)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


}
