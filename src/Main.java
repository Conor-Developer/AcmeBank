import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
    public class Main {

        //create a map containing the account holder as object corresponding to his own ID (Integer)
        private static Map<Integer, AccountHolder> customers = new HashMap<>();
        public static void main(String[] args) {

            //Firstly, the teller must log into the system
            //Display message to user
            System.out.println("******* ACME BANK *******");
            System.out.println("Authorised Personnel Only!");

            //Declare login values variables
            String username="Admin";
            String password = "Password1234";
            Scanner input = new Scanner(System.in);

            //check for user authentication by calling login method, taking as parameters the username, password, max 3 attempt and user input
            boolean authenticated = login(username,password, 3, input);
            if (authenticated) {
                //display welcome message to user
                System.out.println("*************************************************************");
                System.out.println("\tWelcome to Acme Bank. You are now logged in as Admin.");
                System.out.println("*************************************************************");
                System.out.println();

                menu(); // upon successful login, call menu method

            } else { //otherwise, the user does not have access ot the system
                System.out.println("You are not authorised to access the system!");
            }
        }

        //login method, using Strings username and password , an integer for number of attempts, and the Scanner input to capture user input
        public static boolean login(String username, String password, int numberOfAttempts, Scanner input) {
            System.out.print("Please insert your username:");
            //catch user input
            String u = input.nextLine();
            //check whether the username value is empty and if so, loop until the user inserts a value
            while (u.isEmpty()) {
                System.out.print("No input detected. Please insert your username: ");
                u = input.nextLine();
            }
            //loop for password input, allowing only maximum 3 attempts
            for (int attempt = 1; attempt <= numberOfAttempts; attempt++) {
                System.out.print("Please insert your password: ");
                String p = input.nextLine();
                if //  //if both username and password match, then the user can log in (true)
                    //ignore case differences for username input
                (u.equalsIgnoreCase(username) && p.equals(password)) {
                    return true;
                }
            }
            return false;
        }

        public static void menu(){

            //create objects of the Account Holder and Account so that we can access the methods inside the classes
            AccountHolder accountHolder = new AccountHolder();
            Scanner input = new Scanner(System.in);

            //display menu options to user
            System.out.println("Select one of the options:");
            System.out.println("1. Register New Customer");
            System.out.println("2. View Customer Profile");
            System.out.println("3. Update Customer Details");
            System.out.println("4. Delete Customer Account");
            System.out.println("5. View Bank Account");
            System.out.println("6. Create New Bank Account");
            System.out.println("7. Delete Bank Account");

            //catch user input
            String u = input.nextLine();
            //if the user does not enter data, loop through and prompt him to choose a valid option
            while (u.isEmpty()) {
                System.out.println("No input detected. Please choose one option. ");
                u = input.nextLine();
            }
            switch (u){
                case "1":
                    // call the createAccountHolder method from the AccountHolder class, and assign it to the accountHolder object created
                    accountHolder = accountHolder.createAccountHolder(input);
                    // create map entry using the ID and the account holder object
                    customers.put(accountHolder.getId(), accountHolder);
                    break;
                case "2":
                    accountHolder.viewAccountHolder(input, customers); // call the viewAccountHolder method from AccountHolder class
                    break;
                case "3":
                    accountHolder.updateAccountHolder(input, customers); //call the updateAccountHolder method from AccountHolder class
                    break;
                case "4":
                    accountHolder.removeAccountHolder(input,customers); //call the removeAccountHolder method from AccountHolder class
                    break;
                case "5":
                    // code
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
            //run again the menu upon pressing Enter
            System.out.println("Press Enter to return to Main Menu.");
            input.nextLine();
            menu();
        }
}
