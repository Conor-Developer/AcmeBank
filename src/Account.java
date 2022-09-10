import java.time.LocalDate;

public abstract class Account  implements StandingOrder{
        private static int nextAccNumber = 2000;
        private final int accNumber; //needs to be unique

    private double balance;

        private double loanBalance;

        private final AccountTypes type;
        private boolean incurCharges;

        private LocalDate standingOrderCreationDate;

        private LocalDate standingOrderEndDate;


    public Account(double balance, AccountTypes type, boolean incurCharges) {
        this.accNumber = nextAccNumber;
        nextAccNumber++;
        this.balance = balance;
        this.type = type;
        this.incurCharges = incurCharges;
    }

        public int getAccNumber() {
            return accNumber;
        }


        public double getBalance() {
            return balance;
        }

        public double getLoanBalance() {return loanBalance;}

        public void setLoanBalance(double loanBalance) {this.loanBalance = loanBalance;}


        public void setBalance(double balance) {
            this.balance = balance;
        }

        public AccountTypes getType() {
            return type;
        }


        public boolean isIncurCharges() {
            return incurCharges;
        }


        public void setIncurCharges(boolean incurCharges) {
            this.incurCharges = incurCharges;
        }

        public void withdraw (double amount) {
            this.balance -= amount;
        }

        public void deposit (double amount) {
            this.balance += amount;
        }

        public void loan (double amount) {
            System.out.println("This doesnt allow a loan");

        }


        protected void transfer(double balance) {}

        public LocalDate getStandingOrderCreationDate() {
            return standingOrderCreationDate;
        }

        public void setStandingOrderCreationDate(LocalDate standingOrderCreationDate) {
            this.standingOrderCreationDate = standingOrderCreationDate;
        }

        public LocalDate getStandingOrderEndDate() {
            return standingOrderEndDate;
        }

        public void setStandingOrderEndDate(LocalDate standingOrderEndDate) {
            this.standingOrderEndDate = standingOrderEndDate;
        }



    @Override
    public String toString() {
        String accSortCode = "xx-xx-xx";
        return "Account{" +
                "accSortCode='" + accSortCode + '\'' +
                ", accNumber=" + accNumber +
                ", balance=" + balance +
                ", type=" + type +
                ", incurCharges=" + incurCharges +
                '}';
    }
}
