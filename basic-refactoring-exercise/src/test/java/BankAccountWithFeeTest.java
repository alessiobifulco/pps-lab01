import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.BankAccountWithFee;
import example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BankAccountWithFeeTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new BankAccountWithFee(accountHolder, 0);
    }

    @Test
    void testInitialBalance(){
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), 10000);
        assertEquals(10000, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), 10000);
        bankAccount.deposit(2, 10000);
        assertEquals(10000, bankAccount.getBalance());
    }

    @Test
    void testNegativeDeposit(){
        bankAccount.deposit(accountHolder.id(), -1);
        assertEquals(0, bankAccount.getBalance());
    }

}
