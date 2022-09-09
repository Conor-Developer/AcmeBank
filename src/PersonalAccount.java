public class PersonalAccount extends Account {

    double tempBalance;

    PersonalAccount( double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    @Override
    protected void transfer(double balance) {
        this.setBalance((this.getBalance() - balance));
    }
}


