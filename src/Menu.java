import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/* This is where the Menu class */
public class Menu {
    Scanner input = new Scanner(System.in);
    Bank bank;

    boolean running = true;



    public Menu(Bank bank) {
        this.bank = bank;
    }

    /* This is where the program starts from */
    public static void main(String[] args) {
        Bank acmeBank = new Bank();
        Menu menu = new Menu(acmeBank);
//         Two test accounts created for testing.
        AccountHolder newAccountHolderExample = new AccountHolder("Conor", "Hope", "01/12/64", "London", "E10","07404127758", "gmail", true, true);
        newAccountHolderExample.addAccount(AccountTypes.Personal);

        AccountHolder newAccountHolderExample2 = new AccountHolder("Asif", "Alam", "29/10/99", "London", "E12", "074234455", "gmail", true, true);
        newAccountHolderExample2.addAccount(AccountTypes.Personal);

        menu.bank.addCustomerAccount(newAccountHolderExample.getId(), newAccountHolderExample);
        menu.bank.addCustomerAccount(newAccountHolderExample2.getId(), newAccountHolderExample2);

        welcomeScreen();
        boolean login = login();
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

    /* Shows the options that are display options. */
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

    /* register New Customer */
    public void registerNewCustomer() {
        //Output "create account" script and instructions to the teller
        System.out.println("Please insert the following details:");
        System.out.print("First Name: ");
        String firstName = input.nextLine();
        System.out.print("Surname: ");
        String lastName = input.nextLine();
        System.out.print("Date of birth: ");
        String dob = input.nextLine();
        System.out.print("Address: ");
        String address = input.nextLine();
        System.out.print("Post code: ");
        String postcode = input.nextLine();
        System.out.print("Contact number: ");
        String contactNumber = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();

        //verify the customer has provided photo ID
        System.out.println("Identification provided? Y / N");
        char photoIdentificationProvided = input.next().charAt(0);

        //if yes, set boolean photoId to being true§ (false by default)
        boolean photoId;
        //if not, the user will not be able to register a new customer
        photoId = photoIdentificationProvided == 'Y';
        //next, verify the customer has provided a proof of address
        System.out.println("Proof of Address provided? Y / N");
        char addressIdentificationProvided = input.next().charAt(0);
        boolean proofOfAddress;
        // if yes, set the boolean as true
        //else, stop the registration process and return to main menu
        proofOfAddress = addressIdentificationProvided == 'Y';


        AccountHolder newAccountHolder = new AccountHolder(firstName, lastName, dob, address, postcode, contactNumber, email, photoId, proofOfAddress );
        // create map entry using the ID and the account holder object
        this.bank.addCustomerAccount(newAccountHolder.getId(), newAccountHolder);

        System.out.println("Done. " + newAccountHolder.getName() + " " + newAccountHolder.getSurname() + " has the following ID: " + newAccountHolder.getId());

    }

            public static boolean login() {
            System.out.println("******* ACME BANK *******");
            System.out.println("Authorised Personnel Only!");

            String username="Admin";
            String password = "Password1234";
            Scanner input = new Scanner(System.in);

            //  ========================================== CREATE AUTH METHOD ======
            boolean authenticated = login(username,password, 3, input);

            if (authenticated) {
                //display welcome message to user
                System.out.println("*************************************************************");
                System.out.println("\tWelcome to Acme Bank. You are now logged in as Admin.");
                System.out.println("*************************************************************");
                System.out.println();

            } else {
                System.out.println("You are not authorised to access the system!");
            }
            return authenticated;
        }




      //login method, using Strings username and password , an integer for number of attempts, and the Scanner input to capture user input
        public static boolean login(String username, String password, int numberOfAttempts, Scanner input) {
            //catch user input
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
                        authenticated =  true;
                        break;
                    } else {
                        System.out.println("Access not authorised. Try again");
                        attempt++;
                    }
                }
            }

            return authenticated;
        }

    protected void chooseOption() {

        String u = input.nextLine();

        int foundCustomer;

        while (u.isEmpty()) {
            System.out.println("No input detected. Please choose one option. ");
            u = input.nextLine();
        }
        switch (u){
            case "1":
                registerNewCustomer();
                break;
            case "2":
                System.out.print("Enter Customer ID: ");
                int customerId = input.nextInt();
                System.out.println();
                foundCustomer = bank.findCustomer(customerId);
                this.bank.getCustomerAccounts().get(foundCustomer).viewAccountHolder();
                break;
            case "3":
                System.out.print("Enter Customer ID: ");
                customerId = input.nextInt();
                System.out.println();
                foundCustomer = bank.findCustomer(customerId);
                this.bank.getCustomerAccounts().get(foundCustomer).updateAccountHolder();
                break;
            case "4":
                System.out.print("Enter Customer ID: ");
                customerId = input.nextInt();
                System.out.println();
                foundCustomer = bank.findCustomer(customerId);
                this.bank.removeCustomerAccount(foundCustomer);
                System.out.println("The account has been deleted");
                break;
            case "5":
                System.out.print("Enter Customer ID: ");
                customerId=input.nextInt();
                foundCustomer = bank.findCustomer(customerId);

                System.out.print("Enter Account ID: ");
                int accountId = input.nextInt();
                System.out.println();

                System.out.println("Customer Account number: " + foundCustomer);
                System.out.println("Bank Account number: " + accountId);

                switch (this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getType()) {
                    case Personal :
                        System.out.println("1. Check Balance");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Transfer  ");
                        System.out.println("4. Deposit");
                        System.out.println("5. Standing Order");
                        System.out.println("7. Create Loan");
                        System.out.println("8. Pay Loan");

                        String userInput = input.nextLine();
                        while (userInput.isEmpty()) {
                            System.out.println("No input detected. Please choose one option. ");
                            userInput = input.nextLine();
                        }

                        switch (userInput) {
                            case "1":
                                System.out.println("The balance is " + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
                                break;
                            case "2":
                                System.out.println("Enter the amount you want to withdraw :");
                                double amountToWithdraw = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).withdraw(amountToWithdraw);
                                System.out.println("You have withdrawn " + amountToWithdraw + ". The new balance is " + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
                                break;
                            case "3":
                                // Transfer Method
                                System.out.println("Enter the account number of the payee: ");
                                int payeeId = input.nextInt();
                                int foundPayeeBankAccount = bank.findBankAccount(payeeId);
                                int foundPayeeCustomerAccount = bank.findAccountHolderId(foundPayeeBankAccount);

                                System.out.print("How much do you want to transfer?: ");
                                double amountToTransfer = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).transfer(amountToTransfer);
                                double payeeBalance = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getAccount().get(foundPayeeBankAccount).getBalance();
                                this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getAccount().get(foundPayeeBankAccount).setBalance(payeeBalance + amountToTransfer);
                                String payeeFirstName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getName();
                                String payeeLastName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getSurname();
                                System.out.println("You have transferred £" + amountToTransfer + " to " + payeeFirstName + " " + payeeLastName);
                                break;
                            case "4":
                                System.out.println("How much would you like to Deposit: ");
                                double amountToDeposit = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).deposit(amountToDeposit);
                                System.out.println("You deposited " + amountToDeposit + ". The new balance is " + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
                                break;

                                case "5":
                                System.out.println("Enter the account number of the payee: ");
                                payeeId = input.nextInt();
                                foundPayeeBankAccount = bank.findBankAccount(payeeId);
                                foundPayeeCustomerAccount = bank.findAccountHolderId(foundPayeeBankAccount);
                                System.out.println("Enter Amount: ");
                                double standingOrderAmount = input.nextDouble();
                                System.out.println("Enter Frequency of payments");
                                System.out.println("1. Daily");
                                System.out.println("2. Weekly");
                                System.out.println("3. Monthly");
                                String choosePaymentOptions = input.nextLine();
                                String frequency;
                                    switch (choosePaymentOptions) {
                                        case "1" -> frequency = "daily";
                                        case "2" -> frequency = "weekly";
                                        case "3" -> frequency = "monthly";
                                    }

                                System.out.println("When do you want the payments to start?: ");
                                String startDateInput = input.nextLine();
                                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate standingOrderCreationDate = LocalDate.parse(startDateInput, dateFormat);
                                System.out.println("Your payments will start from " + String.format(startDateInput));
                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).setStandingOrderCreationDate(standingOrderCreationDate);
                                System.out.println("When do you want the payments to end?: ");
                                String endDateInput = input.nextLine();
                                LocalDate standingOrderEndDate = LocalDate.parse(endDateInput, dateFormat);
                                System.out.println("Your payments will end on " + String.format(endDateInput));
                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).setStandingOrderEndDate(standingOrderEndDate);

                                break;

                            case "7":
                                System.out.println("How much loan do you require: ");
                                double amountToLoan = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).loan(amountToLoan);
                                System.out.println("You have received " + amountToLoan + ". The new balance is " + this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance());
                                //number of payments, monthly payments, (Loan calculator?), interest
                                break;
                            case "8":
                                System.out.println("How much loan do you want to pay: ");
                                double amountToPayLoan = input.nextDouble();
                                double loanBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getLoanBalance();
                                double existingBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getBalance();


                                if (existingBalance >= amountToPayLoan && amountToPayLoan <= loanBalance) {
                                    this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).setBalance(existingBalance-amountToPayLoan);
                                    this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).setLoanBalance(loanBalance-amountToPayLoan);
                                } else if (amountToPayLoan > loanBalance){
                                    System.out.println("You are attempting to pay more than your loan balance. Please try again.");
                                } else  {
                                    System.out.println("You do not have sufficient funds to pay towards your loan balance.");
                                }
                                loanBalance = this.bank.getCustomerAccounts().get(foundCustomer).getAccount().get(accountId).getLoanBalance();

                                System.out.println("This is the existing balance " + existingBalance);
                                System.out.println("This is the amount to pay loan " + amountToPayLoan);
                                System.out.println("This is the loan balance " + loanBalance);
                                break;
                        }
                        break;
                    case ISA :
                        System.out.println("1. ");
                        System.out.println("2. ");
                    case Business:
                        System.out.println("1. ");
                        System.out.println("2. ");
                        System.out.println("4. Direct Debit");
                }

                break;
            case "6":
                System.out.print("Enter Customer ID: ");
                customerId = input.nextInt();
                System.out.println();
                foundCustomer = bank.findCustomer(customerId);
                System.out.println(foundCustomer);
                System.out.println("What kind of account do you want to create?");
                System.out.println("1. Personal account");
                System.out.println("2. ISA account");
                System.out.println("3. Business account");
                String option = input.nextLine();
                while (option.isEmpty()) {
                            System.out.println("No input detected. Please choose one option. ");
                            option = input.nextLine();
                        }
                switch (option) {
                    case "1" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Personal);
                    case "2" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.ISA);
                    case "3" -> this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Business);
                }

                break;
            case "7":
                System.out.print("Enter Customer ID: ");
                customerId = input.nextInt();
                System.out.print("Enter Account ID: ");
                accountId = input.nextInt();
                System.out.println();

                foundCustomer = bank.findCustomer(customerId);

                this.bank.getCustomerAccounts().get(foundCustomer).removeCustomerAccount(accountId);

                System.out.println("The account has been deleted");
                break;
            case "8":
                running = false;
                break;
            default:
                System.out.println("Please select a valid option.");
        }

    }
}
