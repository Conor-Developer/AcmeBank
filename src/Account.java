import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/*The Bank account super class that implements Standing Order Interface */
public abstract class Account implements StandingOrder {
    private static int nextAccNumber = 2000; // Bank Account number starts from 2000 an auto increments
    private final int accNumber; // needs to be unique

    private double balance; // Account balance

    private double loanBalance; // This variable keeps track of how much loan has been take.

    private final AccountTypes type; // Three types of accounts from enum class.
    private final boolean incurCharges; // Charge the account

    private LocalDate standingOrderCreationDate; // The date when a standing order is created.

    private LocalDate standingOrderEndDate; // The date when a standing order ends.

    private final ArrayList<String> transactions; // Keeps track of all the transactions.

    private final ArrayList<String> standingOrders; // Keeps track of all the standing Orders.

    /*
     * Account constructor
     * Creates an account and adds it to transactions
     */
    public Account(double balance, AccountTypes type, boolean incurCharges) {
        this.accNumber = nextAccNumber;
        nextAccNumber++;
        this.balance = balance;
        this.type = type;
        this.incurCharges = incurCharges;
        transactions = new ArrayList<>();
        standingOrders = new ArrayList<>();
        addTransaction(String.format("Current Balance: - " + NumberFormat.getCurrencyInstance().format(getBalance())));
    }

    // Getter
    public int getAccNumber() {
        return accNumber;
    }

    // Getter
    public double getBalance() {
        return balance;
    }

    // Getter
    public double getLoanBalance() {
        return loanBalance;
    }

    // Setter
    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    // Setter
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter
    public AccountTypes getType() {
        return type;
    }

    // Withdraw money from an account and add it to transactions.
    public void withdraw(double amount) {
        this.balance -= amount;
        addTransaction(String.format("Withdrawal : " + NumberFormat.getCurrencyInstance().format(amount)));
    }

    // Deposit money to an account and add it to transactions.
    public void deposit(double amount) {
        this.balance += amount;
        addTransaction(String.format("Deposit : " + NumberFormat.getCurrencyInstance().format(amount)));
    }

    // Loan
    public void loan(double amount) {
        System.out.println("This doesnt allow a loan");
    }

    // Getter
    public LocalDate getStandingOrderCreationDate() {
        return standingOrderCreationDate;
    }

    // Setter
    public void setStandingOrderCreationDate(LocalDate standingOrderCreationDate) {
        this.standingOrderCreationDate = standingOrderCreationDate;
    }

    // Getter
    public LocalDate getStandingOrderEndDate() {
        return standingOrderEndDate;
    }

    // Setter
    public void setStandingOrderEndDate(LocalDate standingOrderEndDate) {
        this.standingOrderEndDate = standingOrderEndDate;
    }

    // Add a message to the transactions Arraylist for every transaction.
    public void addTransaction(String message) {
        transactions.add(message);
    }

    // Getter
    public ArrayList<String> getTransactions() {
        return transactions;
    }

    // Getter.
    public ArrayList<String> getStandingOrders() {
        return standingOrders;
    }

    // Add a message to the StandingOrders Arraylist for every standing order
    // created.
    public void addStandingOrders(String message) {
        standingOrders.add(message);
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
