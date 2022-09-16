import java.time.LocalDate;

//Standing Order interface that is implemented in the account superclass
public interface StandingOrder {
    LocalDate getStandingOrderCreationDate();

    void setStandingOrderCreationDate(LocalDate standingOrderCreationDate);

    LocalDate getStandingOrderEndDate();

    void setStandingOrderEndDate(LocalDate standingOrderEndDate);
}
