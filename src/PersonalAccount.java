import java.text.NumberFormat;

//Personal account is a child class of the account superclass.
public class PersonalAccount extends Account {

    double tempBalance;

    // Constructor
    PersonalAccount(double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    // Tak out a loan of a given amount
    @Override
    public void loan(double amount) {
        this.setBalance(this.getBalance() + amount);
        this.setLoanBalance(amount);
        addTransaction(
                String.format("You have taken a loan of - " + NumberFormat.getCurrencyInstance().format(amount)));
    }

}
