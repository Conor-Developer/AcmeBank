import java.time.LocalDate;

public class ISAAccount extends Account {
    double tempBalance;

    ISAAccount(double balance, AccountTypes accountType, boolean incurCharges) {
        super(balance, accountType, incurCharges);
        this.tempBalance = this.getBalance();
    }

    @Override
    public LocalDate getStandingOrderCreationDate() {
        return null;
    }

    @Override
    public void setStandingOrderCreationDate(LocalDate standingOrderCreationDate) {
        System.out.println("You can not create a Standing Order with this account");
    }

    @Override
    public LocalDate getStandingOrderEndDate() {
        return null;
    }

    @Override
    public void setStandingOrderEndDate(LocalDate standingOrderEndDate) {
        System.out.println("You can not create a Standing Order with this account");
    }
}
