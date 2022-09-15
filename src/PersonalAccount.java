import java.text.NumberFormat;

public class PersonalAccount extends Account{

    double tempBalance;

    PersonalAccount( double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }
    @Override
    public void loan (double amount) {
        this.setBalance(this.getBalance() + amount);
        this.setLoanBalance(amount);
        addTransaction(String.format("You have taken a loan of - " + NumberFormat.getCurrencyInstance().format(amount)));
    }

}


