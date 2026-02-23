import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    private static final int INITIAL_AMOUNT = 0;
    private static final int BASIC_DEPOSIT = 100;
    private static final int WRONG_ID = 2;


    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_AMOUNT);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        assertEquals(BASIC_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        bankAccount.deposit(WRONG_ID, BASIC_DEPOSIT);
        assertEquals(BASIC_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        bankAccount.withdraw(accountHolder.id(), BASIC_DEPOSIT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        bankAccount.withdraw(WRONG_ID, BASIC_DEPOSIT);
        assertEquals(BASIC_DEPOSIT, bankAccount.getBalance());
    }

}
