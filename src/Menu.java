import java.util.Map;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    Bank bank;


    public Menu(Bank bank) {
        this.bank = bank;
    }

    public static void main(String[] args) {
        Bank acmeBank = new Bank();
        Menu menu = new Menu(acmeBank);
        AccountHolder newAccountHolderExample = new AccountHolder("Conor", "Hope", "16/11/94", "London", "E10", 07435435, "gmail", true, true);
        newAccountHolderExample.addAccount(AccountTypes.Personal);

        AccountHolder newAccountHolderExample2 = new AccountHolder("Conor", "Hope", "16/11/94", "London", "E10", 07435435, "gmail", true, true);
        newAccountHolderExample2.addAccount(AccountTypes.Personal);

        menu.bank.addCustomerAccount(newAccountHolderExample.getId(), newAccountHolderExample);
        menu.bank.addCustomerAccount(newAccountHolderExample2.getId(), newAccountHolderExample2);
        System.out.println();
        System.out.println("**********");
        System.out.println(menu.bank.findBankAccount(4444));


        welcomeScreen();
        displayOptions();
        menu.chooseOption();
        displayOptions();
        menu.chooseOption();
        displayOptions();
        menu.chooseOption();
        displayOptions();
        menu.chooseOption();
        displayOptions();
        menu.chooseOption();
    }

    public static void welcomeScreen() {
        System.out.println("******* ACME BANK *******");
        System.out.println("Authorised Personnel Only!");
        System.out.println();

//        String username="Admin";
//        String password = "Password1234";
        Scanner input = new Scanner(System.in);
    }

    public static void displayOptions() {
        System.out.println("1. Register New Customer");
        System.out.println("2. View Customer Profile");
        System.out.println("3. Update Customer Details");
        System.out.println("4. Delete Customer Account");
        System.out.println("5. View Bank Account");
        System.out.println("6. Create New Bank Account");
        System.out.println("7. Delete Bank Account");
        System.out.println();
        System.out.print("Select one of the options: ");
    }

    public void registerNewCustomer() {
        //Output "create account" script and instructions to the teller
        System.out.println("Please insert the following details:");
        System.out.print("First Name: ");
        String firstName = input.nextLine(); //add user input as account holder name
        System.out.print("Surname: ");
        String lastName = input.nextLine(); //add user input as account holder surname
        System.out.print("Date of birth: ");
        String dob = input.nextLine(); //add user input as account holder DOB
        System.out.print("Address: ");
        String address = input.nextLine(); //add user input as account holder address
        System.out.print("Post code: ");
        String postcode = input.nextLine(); //add user input as account holder post code
        System.out.print("Contact number: ");
        int contactNumber = input.nextInt(); //add user input as account holder phone number
        System.out.print("Email: ");
        String email = input.nextLine(); //add user input as account holder email
        //verify the customer has provided photo ID
        System.out.println("Identification provided? Y / N");
        //take as user input only the first character (index 0)
        char photoIdentificationProvided = input.next().charAt(0);

        //if yes, set boolean photoId to being true§ (false by default)
        boolean photoId;
        if (photoIdentificationProvided == 'Y') {
            photoId = true;
        }
        //if not, the user will not be able to register a new customer
        else {
            photoId = false;
            // System.out.println("Sorry, all account holders require a valid photo ID");
            //return to main menu
            // Main.menu();
        }
        //next, verify the customer has provided a proof of address
        System.out.println("Proof of Address provided? Y / N");
        char addressIdentificationProvided = input.next().charAt(0);
        boolean proofOfAddress;
        // if yes, set the boolean as true
        if (addressIdentificationProvided == 'Y') {
            proofOfAddress = true;
        }
        //else, stop the registration process and return to main menu
        else {
            proofOfAddress = false;
            //  System.out.println("Sorry, all account holders require to be UK residents.");
            // return to main menu
            // Main.menu();
        }


        // =============== NEW ACCOUNT HOLDER CREATED =================================
        // call the createAccountHolder method from the AccountHolder class, and assign it to the accountHolder object created
        AccountHolder newAccountHolder = new AccountHolder(firstName, lastName, dob, address, postcode, contactNumber, email, photoId, proofOfAddress );
        // create map entry using the ID and the account holder object
        this.bank.addCustomerAccount(newAccountHolder.getId(), newAccountHolder);

        System.out.println("Done. " + newAccountHolder.getName() + " " + newAccountHolder.getSurname() + " has the following ID: " + newAccountHolder.getId());


//        next, call method to create a bank account?
//        System.out.println("Press Enter to return to Main Menu.");
//        input.nextLine();
//        Main.menu();
//        return newAccountHolder;
    }

    protected void chooseOption() {
        //  catch user input

        String u = input.nextLine();

        AccountHolder updateAccountHolder = null;
        Account updateAccount = null;
        int foundAccount = 0;
        int foundCustomer = 0;

        //if the user does not enter data, loop through and prompt him to choose a valid option
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

                switch (this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).getType()) {
                    case Personal :
                        System.out.println("1. Check Balance");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Transfer  ");
                        System.out.println("4. Deposit");
                        System.out.println("5. Standing Order");


                        String userInput = input.nextLine();
                        while (userInput.isEmpty()) {
                            System.out.println("No input detected. Please choose one option. ");
                            userInput = input.nextLine();
                        }

                        switch (userInput) {
                            case "1":
                                System.out.println("The balance is " + this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).getBalance());
                                break;
                            case "2":
                                System.out.println("Enter the amount you want to withdraw :");
                                double amountToWithdraw = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).withdraw(amountToWithdraw);
                                System.out.println("You have withdrawn " + amountToWithdraw + ". The new balance is " + this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).getBalance());
                                break;
                            case "3":
                                // Transfer Method
                                System.out.println("Enter the account number of the payee: ");
                                int payeeId = input.nextInt();
                                int foundPayeeBankAccount = bank.findBankAccount(payeeId);
                                int foundPayeeCustomerAccount = bank.findAccountHolderId(foundPayeeBankAccount);

                                System.out.print("How much do you want to transfer?: ");
                                double amountToTransfer = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).transfer(amountToTransfer);
                                double payeeBalance = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).account.get(foundPayeeBankAccount).getBalance();
                                this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).account.get(foundPayeeBankAccount).setBalance(payeeBalance + amountToTransfer);
                                String payeeFirstName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getName();
                                String payeeLastName = this.bank.getCustomerAccounts().get(foundPayeeCustomerAccount).getSurname();
                                System.out.println("You have transferred £" + amountToTransfer + " to " + payeeFirstName + " " + payeeLastName);
                                break;
                            case "4":
                                System.out.println("How much would you like to Deposit: ");
                                double amountToDeposit = input.nextDouble();

                                this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).deposit(amountToDeposit);
                                System.out.println("You have withdrawn " + amountToDeposit + ". The new balance is " + this.bank.getCustomerAccounts().get(foundCustomer).account.get(accountId).getBalance());
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
                                    case "1":
                                        frequency = "daily";
                                        break;
                                    case "2":
                                        frequency = "weekly";
                                        break;
                                    case "3":
                                        frequency = "monthly";
                                        break;
                                }
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
                // call method for creating a new account upon user choice
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
                    case "1":
                        this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Personal);
                        break;
                    case "2":
                        this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.ISA);
                        break;
                    case "3":
                        this.bank.getCustomerAccounts().get(foundCustomer).addAccount(AccountTypes.Business);
                        break;
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
            default:
                System.out.println("Please select a valid option.");
        }

    }
}
