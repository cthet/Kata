package main.java.com.bankAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();

    private DateTimeProvider dateTimeProvider;


    public BankAccount(DateTimeProvider dateTimeProvider){
        this.balance = BigDecimal.ZERO;
        this.dateTimeProvider = dateTimeProvider;
    }

    public void makeADeposit(BigDecimal amount) {
        checkIfAmountIsNull(amount);
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Deposit must be higher than 0.");
        }
        registerTransaction(Operation.deposit, amount);
        this.balance = this.balance.add(amount);
    }

    public void makeAWithDrawal(BigDecimal amount) {
        checkIfAmountIsNull(amount);
        if(amount.compareTo(balance)>0){
            throw new IllegalArgumentException("Withdrawal must be lower or equals to the balance.");
        }
        registerTransaction(Operation.withdrawal, amount);
        this.balance = this.balance.subtract(amount);
    }

    private void registerTransaction(Operation operation, BigDecimal amount) {
        Transaction transaction = new Transaction(operation, amount, dateTimeProvider);
        transactions.add(transaction);
    }

    private static void checkIfAmountIsNull(BigDecimal amount) {
        if(amount ==null){
            throw new IllegalArgumentException("amount must be not null");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }
}
