package test.java.com.bankAccount;


import main.java.com.bankAccount.BankAccount;
import main.java.com.bankAccount.DateTimeProvider;
import main.java.com.bankAccount.FakeDateTimeProvider;
import main.java.com.bankAccount.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class bankAccountAccountTest {
    
    BankAccount bankAccount;
    DateTimeProvider dateTimeProvider = new FakeDateTimeProvider(new Date());
    
    @BeforeEach
    void setup(){
        bankAccount = new BankAccount(dateTimeProvider);
    }

    @Test
    void shouldMakeADepositIfDepositIsStrictlyHigherThan0(){
        makeADepositOf10();
        assertEquals(BigDecimal.TEN, bankAccount.getBalance());
    }

    @Test
    void shouldThrowAnExceptionIfDepositIsNegative(){
        assertThrows(IllegalArgumentException.class, ()->bankAccount.makeADeposit(BigDecimal.valueOf(-1)));
    }

    @Test
    void shouldThrowAnExceptionIfDepositIs0(){
        assertThrows(IllegalArgumentException.class, ()->bankAccount.makeADeposit(BigDecimal.valueOf(0)));
    }

    @Test
    void shouldThrowAnExceptionIfDepositIsnull(){
        assertThrows(IllegalArgumentException.class, ()->bankAccount.makeADeposit(null));
    }

    @Test
    void shouldMakeAWithdrawalIfItIsLowerThanMyBalance(){
        makeADepositOf10();
        bankAccount.makeAWithDrawal(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(5),bankAccount.getBalance());
    }

    @Test
    void shouldThrowAnExceptionIfWithdrawalIsHigherThanMyBalance(){
        makeADepositOf10();
        assertThrows(IllegalArgumentException.class, ()->bankAccount.makeAWithDrawal(BigDecimal.valueOf(11)));
    }

    @Test
    void shouldThrowAnExceptionIfAmountIsnull(){
        assertThrows(IllegalArgumentException.class, ()->bankAccount.makeAWithDrawal(null));
    }


    @Test
    void shouldRegisterAHistoryOfATransaction(){
        makeADepositOf10();
        assertEquals(1,bankAccount.getTransactions().size());
        assertEquals(Operation.deposit,bankAccount.getTransactions().get(0).getOperation());
        assertEquals(dateTimeProvider.now(),bankAccount.getTransactions().get(0).getDate());
        assertEquals(BigDecimal.TEN, bankAccount.getTransactions().get(0).getAmount());
        assertEquals(BigDecimal.TEN, bankAccount.getBalance());
    }

    @Test
    void shouldRegisterAHistoryOf2Transactions(){
        makeADepositOf10();
        bankAccount.makeAWithDrawal(BigDecimal.valueOf(5));
        assertEquals(2,bankAccount.getTransactions().size());
        assertEquals(Operation.deposit,bankAccount.getTransactions().get(0).getOperation());
        assertEquals(Operation.withdrawal,bankAccount.getTransactions().get(1).getOperation());
        assertEquals(dateTimeProvider.now(),bankAccount.getTransactions().get(0).getDate());
        assertEquals(dateTimeProvider.now(),bankAccount.getTransactions().get(1).getDate());
        assertEquals(BigDecimal.TEN, bankAccount.getTransactions().get(0).getAmount());
        assertEquals(BigDecimal.valueOf(5), bankAccount.getTransactions().get(1).getAmount());
        assertEquals(BigDecimal.valueOf(5), bankAccount.getBalance());
    }

    private void makeADepositOf10() {
        bankAccount.makeADeposit(BigDecimal.TEN);
    }




}
