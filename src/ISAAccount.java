import java.time.LocalDate;

public class ISAAccount extends Account{
    double tempBalance;

    ISAAccount( double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    protected void transfer(double balance) {
        this.setBalance((this.getBalance() - balance));
    }

    @Override
    public LocalDate getStandingOrderCreationDate() {
        LocalDate date  = null;
        return date;
    }

    @Override
    public void setStandingOrderCreationDate(LocalDate standingOrderCreationDate) {
        System.out.println("You can not create a Standing Order with this account");
    }

    @Override
    public LocalDate getStandingOrderEndDate() {
        LocalDate date  = null;
        return date;
    }

    @Override
    public void setStandingOrderEndDate(LocalDate standingOrderEndDate) {
        System.out.println("You can not create a Standing Order with this account");
    }



}
