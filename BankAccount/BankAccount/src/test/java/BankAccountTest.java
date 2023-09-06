import org.example.BankAccount;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BankAccountTest {
    private BankAccount account;

    @Before
    public void init() {
        account = new BankAccount();
    }

    @Test
    public void deposit_ValidAmount_UpdatesBalance() {
        account.deposit(BigDecimal.valueOf(100.00));
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance());
    }

    @Test
    public void deposit_NegativeAmount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(new BigDecimal("-10"));
        });
    }

    @Test
    public void deposit_NullAmount_ThrowsException() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(null);
        });
    }

    @Test
    public void withdraw_ValidAmount_UpdatesBalance() {
        account.deposit(BigDecimal.valueOf(500.00));
        account.withdraw(BigDecimal.valueOf(200.00));
        assertEquals(BigDecimal.valueOf(300.00), account.getBalance());
    }

    @Test
    public void withdraw_NegativeAmount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(new BigDecimal("-10"));
        });
    }

    @Test
    public void withdraw_NullAmount_ThrowsException() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(null);
        });
    }

    @Test
    public void withdraw_AmountExceedsBalance_ThrowsException() {
        account.deposit(BigDecimal.valueOf(200.00));
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(BigDecimal.valueOf(500.00));
        });
    }
}
