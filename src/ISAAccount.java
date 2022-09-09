public class ISAAccount extends Account{
    double tempBalance;

    ISAAccount( double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    protected void transfer(double balance) {
        this.setBalance((this.getBalance() - balance));
    }



}
