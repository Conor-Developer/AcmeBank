public abstract class Account {

        //declare variables as private - encapsulation

        //account sort code is final as all accounts have the same sort code
        private final String accSortCode = "xx-xx-xx";
        private int accNumber; //needs to be unique
        private double balance;
        private AccountTypes type; // there can only be 3 pre-established types of accounts (enum)
        private boolean incurCharges;

    public Account(int accNumber, double balance, AccountTypes type, boolean incurCharges) {
        this.accNumber = accNumber;
        this.balance = balance;
        this.type = type;
        this.incurCharges = incurCharges;
//        this.accountHolder = accountHolder;
    }

        //each account has a customer
        private AccountHolder accountHolder;

        //access private variables via public methods
        //getters and setters

        //get account sort code (no need to set as it has been declared as final
        public String getAccSortCode() {
            return accSortCode;
        }

        // get account number
        public int getAccNumber() {
            return accNumber;
        }

        //set account number
        public void setAccNumber(int accNumber) {
            this.accNumber = accNumber;
        }

        //get balance
        public double getBalance() {
            return balance;
        }

        //set balance
        public void setBalance(double balance) {
            this.balance = balance;
        }

        //account type

        public AccountTypes getType() {
            return type;
        }

        public void setType(AccountTypes type) {
            this.type = type;
        }

        //get boolean incurCharges
        public boolean isIncurCharges() {
            return incurCharges;
        }

        //set boolean incurCharges
        public void setIncurCharges(boolean incurCharges) {
            this.incurCharges = incurCharges;
        }

        //get account holder
        public AccountHolder getAccountHolder() {
            return accountHolder;
        }

        //set account holder
        public void setAccountHolder(AccountHolder accountHolder) {
            this.accountHolder = accountHolder;
        }

        public void withdraw (double amount) {
            this.balance -= amount;
        }

        public void deposit (double amount) {
            this.balance += amount;
        }

    @Override
    public String toString() {
        return "Account{" +
                "accSortCode='" + accSortCode + '\'' +
                ", accNumber=" + accNumber +
                ", balance=" + balance +
                ", type=" + type +
                ", incurCharges=" + incurCharges +
                ", accountHolder=" + accountHolder +
                '}';
    }

    //generate public constructor


        //declare abstract methods common to all types of accounts,
        // to ensure they are implemented in each account class

    // Commented out methods below because it's forcing
    // child class to create these methods since they are blank in this class - Conor

//        public abstract void createAccount(); // to be personalised upon type of account
//        public abstract void checkBalance();
//        public abstract void withdraw();
//        public abstract void deposit();

}
