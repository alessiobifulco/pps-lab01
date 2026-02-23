package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;
    private static final int FEE = 1;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        this.balance += (checkUser(userID) && isDepositAllowed(amount)) ? amount : 0;
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        this.balance -= (checkUser(userID) && isWithdrawAllowed(amount)) ? amount : 0;
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount && amount > 0;
    }

    private boolean isDepositAllowed(final double amount){
        return amount > 0;
    }

    private boolean checkUser(final int id) {
        return this.holder.id() == id;
    }

    @Override
    public void withdrawWithFEE(int userID, double basicWithdraw) {
        this.balance -= (checkUser(userID) && isWithdrawAllowed(basicWithdraw + FEE)) ? (basicWithdraw + FEE) : 0;
    }
}
