import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/* This is the Menu class where the program is run from.
* The main method is located here.*/
public class Menu {
    Scanner input = new Scanner(System.in);
    Bank bank;
    boolean running = true; // boolean to check if the menu is running.

    // Menu constructor
    public Menu(Bank bank) {
        this.bank = bank;
    }

    /* This is where the program starts from */
    public static void main(String[] args) {
        Bank acmeBank = new Bank();
        Menu menu = new Menu(acmeBank);
        // Three test accounts created for testing.
        AccountHolder newAccountHolderExample = new AccountHolder("Conor", "Hope", "01/12/64", "London", "E10",
                "07404127758", "gmail", true, true);
        newAccountHolderExample.addAccount(AccountTypes.Personal);

        AccountHolder newAccountHolderExample2 = new AccountHolder("Asif", "Alam", "29/10/99", "London", "E12",
                "074234455", "gmail", true, true);
        newAccountHolderExample2.addAccount(AccountTypes.Personal);

        AccountHolder newAccountHolderExample3 = new AccountHolder("Haider", "Alam", "21/12/00", "London", "E12",
                "074234455", "gmail", true, true);
        newAccountHolderExample3.addAccount(AccountTypes.ISA);

        menu.bank.addCustomerAccount(newAccountHolderExample.getId(), newAccountHolderExample);
        menu.bank.addCustomerAccount(newAccountHolderExample2.getId(), newAccountHolderExample2);
        menu.bank.addCustomerAccount(newAccountHolderExample3.getId(), newAccountHolderExample3);

        // The login screen
        boolean login = login();
        // Checks if the menu is running and the user has logged in and then shows the
        // rest of the options
        while (menu.running && login) {
            displayOptions();
            menu.chooseOption();
        }
    }

    /* The Welcome Screen */
    public static void welcomeScreen() {
        System.out.println("******* ACME BANK *******");
        System.out.println("Authorised Personnel Only!");
        System.out.println();
    }

    /* Shows the options that are displayed. */
    public static void displayOptions() {
        System.out.println("1. Register New Customer");
        System.out.println("2. View Customer Profile");
        System.out.println("3. Update Customer Details");
        System.out.println("4. Delete Customer Account");
        System.out.println("5. View Bank Account");
        System.out.println("6. Create New Bank Account");
        System.out.println("7. Delete Bank Account");
        System.out.println("8. Exit");
        System.out.println();
        System.out.print("Select one of the options: ");
    }

    // Checks if the user input is a string or not.
    public String stringValidation(String message) {
        boolean isString = false;
        String stringInput = null;
        while (!isString) {
            System.out.print(message);
            stringInput = input.nextLine();
            try {
                Integer.parseInt(stringInput);
                System.out.println("You entered a Number. Please try again.");
            } catch (NumberFormatException e) {
                isString = true;
            }
        }
        return stringInput;
    }

    // Checks if the user input is an integer or not.
    public String intValidation(String message) {
        boolean isInt = false;
        String stringInput = null;
        while (!isInt) {
            System.out.print(message);
            stringInput = input.nextLine();
            try {
                Integer.parseInt(stringInput);
                isInt = true;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a Number. Please try again.");
            }
        }
        return stringInput;
    }

    // Checks if the user input is a double or not.
    public String doubleValidation(String message) {
        boolean isDouble = false;
        String stringInput = null;
        while (!isDouble) {
            System.out.print(message);
            stringInput = input.nextLine();
            try {
                Double.parseDouble(stringInput);
                isDouble = true;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a Number. Please try again.");
            }
        }
        return stringInput;
    }

    /* register New Customer */
    public void registerNewCustomer() {
        // Output "create account" script and instructions to the teller
        System.out.println("Please insert the following details:");
        String firstName = stringValidation("First Name: ");
        String lastName = stringValidation("Surname: ");
        String dob = stringValidation("Date of birth: ");
        String address = stringValidation("Address: ");
        String postcode = stringValidation("Post code: ");
        String contactNumber = intValidation("Contact number: ");
        String email = stringValidation("Email: ");

        // verify the customer has provided photo ID
        boolean photoId = false;
        char photoIdentificationProvided = stringValidation("Identification provided? Y / N: ").toLowerCase().charAt(0);
        if (photoIdentificationProvided == 'n') {
            System.out.println("You need Photo ID for an account.");
            while (this.running) {
                displayOptions();
                chooseOption();
            }
        } else {
            photoId = true;
        }

        // next, verify the customer has provided a proof of address
        char addressIdentificationProvided = stringValidation("Proof of Address provided? Y / N: ").toLowerCase()
                .charAt(0);
        boolean proofOfAddress = false;
        if (addressIdentificationProvided == 'n') {
            System.out.println("You need Proof of Address for an account.");
            while (this.running) {
                displayOptions();
                chooseOption();
            }
        } else {
            proofOfAddress = true;
        }

        AccountHolder newAccountHolder = new AccountHolder(firstName, lastName, dob, address, postcode, contactNumber,
                email, photoId, proofOfAddress);
        // create map entry using the ID and the account holder object
        this.bank.addCustomerAccount(newAccountHolder.getId(), newAccountHolder);

        System.out.println("Done. " + newAccountHolder.getName() + " " + newAccountHolder.getSurname()
                + " has the following ID: " + newAccountHolder.getId());

    }

    // view the account holder info
    public void viewAccountHolder() {
        String customerIdInput = intValidation("Enter Customer ID: ");
        int customerId = Integer.parseInt(customerIdInput);
        System.out.println();
        int foundCustomer = bank.findCustomer(customerId);
        this.bank.getCustomerAccounts().get(foundCustomer).viewAccountHolder();
    }

    // find the account holder with the given ID and update account holder
    public void updateAccountHolder() {
        String customerIdInput = intValidation("Enter Customer ID: ");
        int customerId = Integer.parseInt(customerIdInput);
        System.out.println();
        int foundCustomer = bank.findCustomer(customerId);
        this.bank.getCustomerAccounts().get(foundCustomer).updateAccountHolder();
    }

    // find the account holder with the given ID and delete account holder
    public void deleteAccountHolder() {
        String customerIdInput = intValidation("Enter Customer ID: ");
        int customerId = Integer.parseInt(customerIdInput);
        System.out.println();
        int foundCustomer = bank.findCustomer(customerId);
        this.bank.removeCustomerAccount(foundCustomer);
        System.out.println("The account has been deleted");
    }

    // find the account holder with the given ID and create a new bank account for
    // that account holder
    public void createNewBankAccount() {
        String customerIdInput = intValidation("Enter Customer ID: ");
        int customerId = Integer.parseInt(customerIdInput);
        System.out.println();
        int foundCustomer = bank.findCustomer(customerId);
        System.out.println(foundCustomer);
        System.out.println("What kind of account do you want to create?");
        System.out.println("1. Personal account");
        System.out.println("2. ISA account");
        System.out.println("3. Business account");
        String option = input.nextLine();

        // create different accounts based on the user input.
        switch (option) {
            case "1" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Personal);
            case "2" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.ISA);
            case "3" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Business);
        }
    }

    // Enter account holder id and then bank account number and then delete the
    // account with the given info.
    public void deleteBankAccount() {
        String customerIdInput = intValidation("Enter Customer ID: ");
        int customerId = Integer.parseInt(customerIdInput);
        String accountIdInput = intValidation("Enter Account Number: ");
        int accountId = Integer.parseInt(accountIdInput);
        System.out.println();
        int foundCustomer = bank.findCustomer(customerId);
        this.bank.getCustomerAccounts().get(foundCustomer).removeCustomerAccount(accountId);
        System.out.println("The bank account has been deleted");
    }

    // use account holder id and then bank account number and then check the
    // balance of that particular account.
    public void checkBalance(int foundCustomer, int accountId) {
        System.out.println("The balance is "
                + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
    }

    // use account holder id and then bank account number and then the amount to
    // withdraw an then withdraw money from that particular account.
    public void withdrawBalance(int foundCustomer, int accountId) {
        String withdrawalAmountInput = doubleValidation("Enter the amount you want to withdraw :");
        double amountToWithdraw = Double.parseDouble(withdrawalAmountInput);
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).withdraw(amountToWithdraw);
        System.out.println("Withdrawal : " + NumberFormat.getCurrencyInstance().format(amountToWithdraw)
                + " The new balance is - " + NumberFormat.getCurrencyInstance().format(
                        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance()));
    }

    // use account holder id and then bank account number and then the amount to
    // withdraw an then deposit money to that particular account.
    public void depositBalance(int foundCustomer, int accountId) {
        String doubleInput = doubleValidation("How much would you like to Deposit: ");
        double amountToDeposit = Double.parseDouble(doubleInput);
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).deposit(amountToDeposit);
        System.out.println("Deposit : " + NumberFormat.getCurrencyInstance().format(amountToDeposit)
                + " The new balance is - " + NumberFormat.getCurrencyInstance().format(
                        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance()));
    }

    /*
     * use account holder id and then bank account number and then ask for the payee
     * bank account number and then ask for the amount to transfer.
     * To make a transfer withdraw money from the payer ID and deposit money to the
     * payee ID.
     */
    public void transfer(int foundCustomer, int accountId) {
        int foundPayeeBankAccount = bankAccountValidation("Enter the bank account number of the payee: ");
        int foundPayeeCustomerAccount = bank.findAccountHolderId(foundPayeeBankAccount);

        String amountToTransferInput = doubleValidation("How much do you want to transfer?: ");
        double amountToTransfer = Double.parseDouble(amountToTransferInput);

        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).withdraw(amountToTransfer);
        this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getAccount().get(foundPayeeBankAccount)
                .deposit(amountToTransfer);
        String payeeFirstName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getName();
        String payeeLastName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getSurname();
        System.out.println("You have transferred £" + amountToTransfer + " to " + payeeFirstName + " " + payeeLastName);
    }

    // choose Frequency Of Payments for the standing order method
    public String chooseFrequencyOfPayments() {
        String choosePaymentFrequency = intValidation(
                "Frequency of payments: \n 1. Daily \n 2. Weekly \n 3. Monthly \n");

        String frequency = "";
        switch (choosePaymentFrequency) {
            case "1" -> frequency = "daily";
            case "2" -> frequency = "weekly";
            case "3" -> frequency = "monthly";
        }
        return frequency;
    }

    // Choose payment start date for the standing order method
    public String paymentStartDate(int foundCustomer, int accountId) {
        String startDateInput = stringValidation("When do you want the payments to start?: ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate standingOrderCreationDate = LocalDate.parse(startDateInput, dateFormat);
        System.out.println("Your payments will start from " + String.format(startDateInput));
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .setStandingOrderCreationDate(standingOrderCreationDate);
        return startDateInput;
    }

    // Choose payment end date for the standing order method
    public String paymentEndDate(int foundCustomer, int accountId) {
        String endDateInput = stringValidation("When do you want the payments to end?: ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate standingOrderEndDate = LocalDate.parse(endDateInput, dateFormat);
        System.out.println("Your payments will end on " + String.format(endDateInput));
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .setStandingOrderEndDate(standingOrderEndDate);
        return endDateInput;
    }

    // Creates a standing order with given parameters.
    public void standingOrder(int foundCustomer, int accountId) {
        String payeeAccountNumberInput = intValidation("Enter the account number of the payee: ");
        int payeeId = Integer.parseInt(payeeAccountNumberInput);

        String doubleInput = doubleValidation("Enter Amount: ");
        double standingOrderAmount = Double.parseDouble(doubleInput);

        String frequencyOfPayments = chooseFrequencyOfPayments();
        String startDate = paymentStartDate(foundCustomer, accountId);
        String endDate = paymentEndDate(foundCustomer, accountId);
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).addStandingOrders(
                String.format("Payee : " + payeeId + ", Amount : "
                        + NumberFormat.getCurrencyInstance().format(standingOrderAmount) + ", Frequency of payments : "
                        + frequencyOfPayments + ", Start date : " + String.format(startDate) + ", End date: "
                        + String.format(endDate)));
    }

    // View all standing orders a user has.
    public void viewStandingOrder(int foundCustomer, int accountId) {
        System.out.println("**** Standing Orders ****");
        for (String standingOrders : this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .getStandingOrders()) {
            System.out.println(standingOrders);
        }
        System.out.println("=================");
    }

    // create a loan for the user when asking for a certain amount.
    public void createLoan(int foundCustomer, int accountId) {
        String amountToLoanInput = doubleValidation("How much loan do you require: ");
        double amountToLoan = Double.parseDouble(amountToLoanInput);
        this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).loan(amountToLoan);
        System.out.println("You have received " + amountToLoan + ". The new balance is "
                + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
    }

    // Allow the user to pay off their loan.
    public void payLoan(int foundCustomer, int accountId) {
        String amountToPayLoanInput = doubleValidation("How much loan do you want to pay: ");
        double amountToPayLoan = Double.parseDouble(amountToPayLoanInput);

        double loanBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .getLoanBalance();
        double existingBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .getBalance();

        if (existingBalance >= amountToPayLoan && amountToPayLoan <= loanBalance) {
            this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                    .setBalance(existingBalance - amountToPayLoan);
            this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                    .setLoanBalance(loanBalance - amountToPayLoan);
        } else if (amountToPayLoan > loanBalance) {
            System.out.println("You are attempting to pay more than your loan balance. Please try again.");
        } else {
            System.out.println("You do not have sufficient funds to pay towards your loan balance.");
        }
        loanBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getLoanBalance();

        System.out.println("This is the existing balance " + existingBalance);
        System.out.println("This is the amount to pay loan " + amountToPayLoan);
        System.out.println("This is the loan balance " + loanBalance);
    }

    // display all the transactions a user has made.
    public void displayTransactions(int foundCustomer, int accountId) {
        System.out.println("**** Transactions ****");
        for (String transactions : this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId)
                .getTransactions()) {
            System.out.println(transactions);
        }
        System.out.println("=================");
    }

    // Checks if the customer exists in the hashmap or not.
    public int customerValidation(String message) {
        int foundCustomer;
        do {
            String customerIdInput = intValidation(message);
            int customerId = Integer.parseInt(customerIdInput);
            foundCustomer = bank.findCustomer(customerId);
            if (foundCustomer == 0) {
                System.out.println("This account does not exist. Please try again.");
            }
        } while (foundCustomer == 0);

        return foundCustomer;
    }

    // Checks if the bank account exists in the nested hashmap or not.
    public int bankAccountValidation(String message) {
        int accountId;
        do {
            String accountIdInput = intValidation(message);
            int accountIdParse = Integer.parseInt(accountIdInput);
            accountId = this.bank.findBankAccount(accountIdParse);
            if (accountId == 0) {
                System.out.println("This Bank account does not exist. Please try again.");
            }
        } while (accountId == 0);

        return accountId;
    }

    // Allows users to go inside another menu to so they can check balance, withdraw
    // etc
    public void viewBankAccount() {
        int foundCustomer = customerValidation("Enter your customer ID: ");
        int accountId = bankAccountValidation("Enter your bank account ID: ");

        System.out.println();

        System.out.println("Customer Account number: " + foundCustomer);
        System.out.println("Bank Account number: " + accountId);

        // shows a different menu depending on what type of bank account they have.
        switch (this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getType()) {
            // Personal account options
            case Personal -> {
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer  ");
                System.out.println("4. Deposit");
                System.out.println("5. Set up standing Order");
                System.out.println("6. View Standing Orders");
                System.out.println("7. Create Loan");
                System.out.println("8. Pay Loan");
                System.out.println("9. View Transactions");
                String userInput = input.nextLine();
                // different actions based on the user input
                switch (userInput) {
                    case "1" -> checkBalance(foundCustomer, accountId);
                    case "2" -> withdrawBalance(foundCustomer, accountId);
                    case "3" -> transfer(foundCustomer, accountId);
                    case "4" -> depositBalance(foundCustomer, accountId);
                    case "5" -> standingOrder(foundCustomer, accountId);
                    case "6" -> viewStandingOrder(foundCustomer, accountId);
                    case "7" -> createLoan(foundCustomer, accountId);
                    case "8" -> payLoan(foundCustomer, accountId);
                    case "9" -> displayTransactions(foundCustomer, accountId);
                    default -> System.out.println("Please select a valid option.");
                }
            }
            // ISA account options.
            case ISA -> {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. View Transactions");
                String userInput = input.nextLine();
                // Actions
                switch (userInput) {
                    case "1" -> checkBalance(foundCustomer, accountId);
                    case "2" -> depositBalance(foundCustomer, accountId);
                    case "3" -> displayTransactions(foundCustomer, accountId);
                }
            }
            // Business account options.
            case Business -> {
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. International Transfer ");
                System.out.println("4. Deposit");
                System.out.println("5. Set up Direct Debit");
                System.out.println("6. Create Loan");
                System.out.println("7. Pay Loan");
                System.out.println("8. View Transactions");
                String userInput = input.nextLine();
                // actions
                switch (userInput) {
                    case "1" -> checkBalance(foundCustomer, accountId);
                    case "2" -> withdrawBalance(foundCustomer, accountId);
                    case "3" -> transfer(foundCustomer, accountId);
                    case "4" -> depositBalance(foundCustomer, accountId);
                    case "5" -> System.out.println("This feature has not been implemented yet");
                    case "6" -> createLoan(foundCustomer, accountId);
                    case "7" -> payLoan(foundCustomer, accountId);
                    case "8" -> displayTransactions(foundCustomer, accountId);
                    default -> System.out.println("Please select a valid option.");
                }
            }
        }
    }

    // login method and screen
    public static boolean login() {
        welcomeScreen();

        String username = "Admin";
        String password = "Password1234";
        Scanner input = new Scanner(System.in);

        // ========================================== CREATE AUTH METHOD ======
        boolean authenticated = login(username, password, 3, input);

        if (authenticated) {
            // display welcome message to user
            System.out.println("*************************************************************");
            System.out.println("\tWelcome to Acme Bank. You are now logged in as Admin.");
            System.out.println("*************************************************************");
            System.out.println();

        } else {
            System.out.println("You are not authorised to access the system!");
        }
        return authenticated;
    }

    // login method, using Strings username and password , an integer for number of
    // attempts, and the Scanner input to capture user input
    public static boolean login(String username, String password, int numberOfAttempts, Scanner input) {
        // catch user input
        int attempt = 0;

        boolean authenticated = false;

        while (attempt < numberOfAttempts) {
            System.out.print("Enter your username: ");
            String otherUserName = input.nextLine();
            if (otherUserName.isEmpty()) {
                System.out.print("Please enter a correct username.");
            } else {
                System.out.print("Enter your password: ");
                String otherPassword = input.nextLine();
                if (username.equals(otherUserName) && password.equals(otherPassword)) {
                    authenticated = true;
                    break;
                } else {
                    System.out.println("Access not authorised. Try again");
                    attempt++;
                }
            }
        }

        return authenticated;
    }

    // Allows users to select an option from the first menu
    protected void chooseOption() {

        String u = input.nextLine();

        switch (u) {
            case "1" -> registerNewCustomer();
            case "2" -> viewAccountHolder();
            case "3" -> updateAccountHolder();
            case "4" -> deleteAccountHolder();
            case "5" -> viewBankAccount();
            case "6" -> createNewBankAccount();
            case "7" -> deleteBankAccount();
            case "8" -> running = false;
            default -> System.out.println("Please select a valid option.");
        }
    }
}
