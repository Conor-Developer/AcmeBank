import java.time.LocalDate;

public interface StandingOrder {
    LocalDate getStandingOrderCreationDate();

    void setStandingOrderCreationDate(LocalDate standingOrderCreationDate);

    LocalDate getStandingOrderEndDate();

    void setStandingOrderEndDate(LocalDate standingOrderEndDate);
}
