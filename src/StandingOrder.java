import java.time.LocalDate;
public interface StandingOrder {
    public LocalDate getStandingOrderCreationDate();
    public void setStandingOrderCreationDate(LocalDate standingOrderCreationDate);
    public LocalDate getStandingOrderEndDate();
    public void setStandingOrderEndDate(LocalDate standingOrderEndDate);
}
