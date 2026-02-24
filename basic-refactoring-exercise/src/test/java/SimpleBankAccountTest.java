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
    private static final int NEGATIVE_AMOUNT = -1;

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
        depositFollowedByWithdraw(BASIC_DEPOSIT, INITIAL_AMOUNT);
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        bankAccount.withdraw(WRONG_ID, BASIC_DEPOSIT);
        assertEquals(BASIC_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNegativeAmount(){
        depositFollowedByWithdraw(NEGATIVE_AMOUNT, BASIC_DEPOSIT);
    }

    @Test
    void testWithdrawNotAllowedAmount(){
        depositFollowedByWithdraw(BASIC_DEPOSIT+1, BASIC_DEPOSIT);
    }

    @Test
    void testNegativeDeposit(){
        bankAccount.deposit(accountHolder.id(), NEGATIVE_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    private void depositFollowedByWithdraw(double amountToWithdraw, double expected){
        bankAccount.deposit(accountHolder.id(), BASIC_DEPOSIT);
        bankAccount.withdraw(accountHolder.id(), amountToWithdraw);
        assertEquals(expected, bankAccount.getBalance());
    }
}
