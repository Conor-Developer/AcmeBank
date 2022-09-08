import java.util.Map;
import java.util.Scanner;

public class Start {

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

    public static AccountHolder registerNewCustomer(Scanner input, Map<Integer, AccountHolder> customers) {
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

        //set account holder ID ,
        // on the assumption that the teller will introduce a unique ID each time ( eg based on passport or NI number)
        System.out.println("Insert account holder unique ID: ");
        int id = input.nextInt();

        // =============== NEW ACCOUNT HOLDER CREATED =================================
        // call the createAccountHolder method from the AccountHolder class, and assign it to the accountHolder object created
        AccountHolder newAccountHolder = new AccountHolder(id, firstName, lastName, dob, address, postcode, contactNumber, email, photoId, proofOfAddress );
        // create map entry using the ID and the account holder object
        customers.put(newAccountHolder.getId(), newAccountHolder);

        System.out.println("Done. " + newAccountHolder.getName() + " " + newAccountHolder.getSurname() + " has the following ID: " + newAccountHolder.getId());


//        next, call method to create a bank account?
//        System.out.println("Press Enter to return to Main Menu.");
//        input.nextLine();
//        Main.menu();
        return newAccountHolder;
    }

    public static int findCustomer (int customerID, Map<Integer, AccountHolder> customers) {
        int correctId = 0;
        for(Map.Entry<Integer, AccountHolder> values: customers.entrySet()) {
            int AccountId = values.getValue().getId();
            if (AccountId == customerID) {
                correctId = values.getKey();
            }
        }
        return correctId;
    }

    public static AccountHolder chooseOption(Scanner input, Map<Integer, AccountHolder> customers) {
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
                    updateAccountHolder = registerNewCustomer(input, customers);
                    break;
                case "2":
                    System.out.print("Enter Customer ID: ");
                    int customerId = input.nextInt();
                    System.out.println();
                    foundCustomer = findCustomer(customerId, customers);
                    customers.get(foundCustomer).viewAccountHolder();
                    break;
                case "3":
                    System.out.print("Enter Customer ID: ");
                    customerId = input.nextInt();
                    System.out.println();
                    foundCustomer = findCustomer(customerId, customers);
                    updateAccountHolder = customers.get(foundCustomer).updateAccountHolder();
                    break;
                case "4":

                   //  System.out.println("What kind of account do you want to create? (Personal, ISA, Business)");
                    // call method for creating a new account upon user choice
                    // PersonalAccount one = new PersonalAccount(account, balance, type, false)
                    System.out.print("Enter Customer ID: ");
                    customerId = input.nextInt();
                    System.out.println();
                    foundCustomer = findCustomer(customerId, customers);
                    customers.get(foundCustomer).setId(-1);
                    updateAccountHolder = customers.get(foundCustomer);

                    break;
                case "5":
                    System.out.println("Enter Customer ID:");
                    customerId=input.nextInt();
                    foundCustomer = findCustomer(customerId, customers);

                    System.out.println("Customer Account number: " + foundCustomer);

                    if (foundCustomer == 0) {
                        System.out.println("This account does not exist");
                        break;
                    }

                    System.out.println("Enter Account Number:");
                    int accountNumber=input.nextInt();
                    foundAccount = customers.get(foundCustomer).findAccount(accountNumber);

                    System.out.println("Bank Account number:" + accountNumber);
                    updateAccountHolder = customers.get(foundCustomer);
                    updateAccount = updateAccountHolder.account.get(foundAccount);


                    switch (updateAccount.getType()) {
                        case Personal :
                            System.out.println("1. Check Balance");
                            System.out.println("2. Withdraw");
                            System.out.println("3.  ");
                            System.out.println("4. Deposit");
                            Scanner scanner = new Scanner(System.in);
                            String option = scanner.nextLine();
                            while (option.isEmpty()) {
                                System.out.println("No input detected. Please choose one option. ");
                                option = input.nextLine();
                            }
                            switch (option) {
                                case "1":
                                    System.out.println("The balance is " + customers.get(foundCustomer).account.get(foundAccount).getBalance());
                                    break;
                                case "2":
                                    System.out.println("Enter the amount you want to withdraw :");
                                    double amountToWithdraw = scanner.nextDouble();
                                    updateAccount.withdraw(amountToWithdraw);
                                    updateAccountHolder.account.put(foundAccount, updateAccount);
                                    System.out.println("You have withdrawn " + amountToWithdraw + ". The new balance is " + updateAccountHolder.account.get(foundAccount).getBalance());
                                    break;
                                case "4":
                                    System.out.println("How much would you like to Deposit: ");
                                    double amountToDeposit = scanner.nextDouble();
                                    updateAccount.deposit(amountToDeposit);
                                    updateAccountHolder.account.put(foundAccount, updateAccount);
                                    System.out.println("You have deposited " + amountToDeposit + ". The new balance is " + updateAccountHolder.account.get(foundAccount).getBalance());
                            }
                            break;
                        case ISA :
                            System.out.println("1. ");
                            System.out.println("1. ");
                        case Business:
                            System.out.println("1. ");
                            System.out.println("1. ");
                    }

                    break;
                case "6":
                    // call method for creating a new account upon user choice
                    System.out.println("What kind of account do you want to create? (Personal, ISA, Business)");
                    break;
                case "7":
                    // code
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }

        return updateAccountHolder;
    }



    public static void main(String[] args) {

        // create a map containing the account holder as object corresponding to his own ID (Integer)
        Bank acmeBank = new Bank();
        AccountHolder newAccountHolderExample = new AccountHolder(1, "Conor", "Hope", "16/11/94", "London", "E10", 07435435, "gmail", true, true);
        newAccountHolderExample.addAccount();


        int customerId = newAccountHolderExample.getId();
        acmeBank.addCustomerAccount(customerId, newAccountHolderExample);

        Scanner input = new Scanner(System.in);

        welcomeScreen();
        displayOptions();

        AccountHolder updateCustomerAccount = chooseOption(input, acmeBank.getCustomerAccounts());
        if(updateCustomerAccount != null) {
            acmeBank.addCustomerAccount(updateCustomerAccount.getId(), updateCustomerAccount);
        } else if (updateCustomerAccount.getId() == -1) {
            acmeBank.removeCustomerAccount(updateCustomerAccount.getId());
        }

        updateCustomerAccount = chooseOption(input, acmeBank.getCustomerAccounts());
        if(updateCustomerAccount != null) {
            acmeBank.addCustomerAccount(updateCustomerAccount.getId(), updateCustomerAccount);
        }

        updateCustomerAccount = chooseOption(input, acmeBank.getCustomerAccounts());
        if(updateCustomerAccount != null) {
            acmeBank.addCustomerAccount(updateCustomerAccount.getId(), updateCustomerAccount);
        }

        updateCustomerAccount = chooseOption(input, acmeBank.getCustomerAccounts());
        if(updateCustomerAccount != null) {
            acmeBank.addCustomerAccount(updateCustomerAccount.getId(), updateCustomerAccount);
        }

    }
}
