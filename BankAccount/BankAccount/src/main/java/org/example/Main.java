package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(BigDecimal.valueOf(1000.00));
        account.withdraw(BigDecimal.valueOf(500.00));
        account.deposit(BigDecimal.valueOf(200.00));
        account.printStatement();

    }
}