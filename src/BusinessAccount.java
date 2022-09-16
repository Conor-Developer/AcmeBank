/* BusinesAccount that is a child class of Account */
public class BusinessAccount extends Account {
    double tempBalance;

    //Constructor
    BusinessAccount(double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }
}
