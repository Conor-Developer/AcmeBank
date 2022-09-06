public class PersonalAccount extends Account {

    double tempBalance;

    PersonalAccount(int accountNumber, double balance, AccountTypes accountType, boolean incurCharges) {
        super(accountNumber, balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    protected void transfer(double balance) {
        this.setBalance((this.getBalance() - balance));
    }



    public static void main(String[] args) {

        // Bank teller is in customers account...

        // Bank teller presses 1 for bank transfer
        // Bank teller enters amount to be transferred
        // Bank teller enter account id of receiver
        // transfer(500) - reduces balance from customer account
        // bank find customer id
        // bank find accountNumber

        // bank.findAccountProfile(customer id).findCustomerAccount(acccountNum).setBalance(+500)

    }

}


