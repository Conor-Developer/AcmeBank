public class BusinessAccount extends Account{
    double tempBalance;

    BusinessAccount( double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    protected void transfer(double balance) {
        this.setBalance((this.getBalance() - balance));
    }


}
