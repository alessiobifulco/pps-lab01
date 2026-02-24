package example.model;

public class BankAccountWithFee extends SimpleBankAccount{


    private static final int FEE = 1;

    public BankAccountWithFee(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        super.withdraw(userID, amount + FEE);
    }

}
