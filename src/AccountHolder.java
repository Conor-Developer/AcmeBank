import java.util.Map;
import java.util.Scanner;

public class AccountHolder {

    //declare private variables - encapsulation

    private int id; //unique account holder Id
    private String name; // name of account holder
    private String surname; //surname of account holder
    private String dateOfBirth; // date of birth of the account holder
    private String address; //address of account holder
    private String postcode; //postcode of account holder
    private long phoneNumber; //contact number for the account holder
    private String email; //email of account holder

    private boolean photoId = false; //check for photo id

    private boolean proofOfAddress = false; //check for photo id

    private Account account; //account(s) associated with the account holder


    //access private variables through public methods

    //get and set account holder id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //get and set public methods for account holder name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //get and set public methods for account holder name
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    //get and set public methods for account holder date of birth
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //get and set public methods for account holder address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    ////get and set public methods for account holder post code
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    //get and set public methods for account holder phone number
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //get and set public methods for account holder email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //get and set public methods for account holder photoId
    public boolean isPhotoId() {
        return photoId;
    }
    public void setPhotoId(boolean photoId) {
        this.photoId = photoId;
    }

    //get and set public methods for account holder PoA
    public boolean isProofOfAddress() {
        return proofOfAddress;
    }
    public void setProofOfAddress(boolean proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    //get and set public methods for accessing the object Account associated with the account holder
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    /*
    //constructor
    public AccountHolder(int id, String name, String surname, String dateOfBirth, String address, String postCode, int phoneNumber, String email, boolean photoId, boolean proofOfAddress, Account account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photoId = photoId;
        this.proofOfAddress = proofOfAddress;
        this.account = account;
    }
    */
    //empty constructor
    public AccountHolder() {
    }

    //method to create a new account holder and store it as an object
    public AccountHolder createAccountHolder(Scanner input){

        //create object of the AccountHolder
        AccountHolder accountHolder = new AccountHolder();

        //Output "create account" script and instructions to the teller
        System.out.println("Please insert the following details:");
        System.out.print("First Name: ");
        accountHolder.setName(input.nextLine()); //add user input as account holder name
        System.out.print("Surname: ");
        accountHolder.setSurname(input.nextLine()); //add user input as account holder surname
        System.out.print("Date of birth: ");
        accountHolder.setDateOfBirth(input.nextLine()); //add user input as account holder DOB
        System.out.print("Address: ");
        accountHolder.setAddress(input.nextLine()); //add user input as account holder address
        System.out.print("Post code: ");
        accountHolder.setPostcode(input.nextLine()); //add user input as account holder post code
        System.out.print("Contact number: ");
        accountHolder.setPhoneNumber(input.nextLong()); //add user input as account holder phone number
        System.out.print("Email: ");
        accountHolder.setEmail(input.nextLine()); //add user input as account holder email
        //verify the customer has provided photo ID
        System.out.println("Identification provided? Y / N");
        //take as user input only the first character (index 0)
        char p = input.next().charAt(0);

        //if yes, set boolean photoId to being true  (false by default)
        if (p=='Y'){
            accountHolder.setPhotoId(true);
        }
        //if not, the user will not be able to register a new customer
        else{
            System.out.println("Sorry, all account holders require a valid photo ID");
            //return to main menu
            Main.menu();
        }

        //next, verify the customer has provided a proof of address
        System.out.println("Proof of Address provided? Y / N");
        char a = input.next().charAt(0);

        // if yes, set the boolean as true
        if (a =='Y'){
            accountHolder.setProofOfAddress(true);
        }
        //else, stop the registration process and return to main menu
        else{
            System.out.println("Sorry, all account holders require to be UK residents.");
            //return to main menu
            Main.menu();
        }

        //set account holder ID ,
        // on the assumption that the teller will introduce an unique ID each time ( eg based on passport or NI number)
        System.out.println("Insert account holder unique ID: ");
        accountHolder.setId(input.nextInt());

        System.out.println("Done. " + accountHolder.name + " " + accountHolder.surname
                + " has the following ID: " + accountHolder.getId());


        //next, call method to create an account?


        return accountHolder; //return the accountHolder object created
    }

    //method to retrieve a customer record
    public AccountHolder viewAccountHolder (Scanner input, Map<Integer,AccountHolder> customers){
        System.out.println("Enter the account holder's ID: ");

        //store the user input in a customerId variable
        Integer customerId = Integer.valueOf(input.nextLine());

        //assign the customerId variable as ID map key, which would hold an account holder object, if it exists
        AccountHolder accountHolder = customers.get(customerId);

        //if there is no account holder, return user to main menu
        if (accountHolder==null){
            System.out.println("There is no existing account holder associated to this ID.");
            System.out.println("Returning to the Main Menu...");
            Main.menu();
        }
        else { //if there is an object associated with this key, access the values using get() and display details to teller
            System.out.println(" The account holder associated to the " + accountHolder.getId() + " ID is:"
                    +"\n"  + "Full name: " + accountHolder.getName() + " " + accountHolder.getSurname()
                    + "\n"+ "Date of birth: " + accountHolder.getDateOfBirth()
                    + "\n" + "Address: " + accountHolder.getAddress() + ", Postcode: " + accountHolder.getPostcode()
                    +"\n" + "Contact number: " +accountHolder.getPhoneNumber()
                    +"\n" + "Account(s) records: " +accountHolder.getAccount() );

            //ask user whether records need to be updated
            System.out.println(" Would you like to update these records? Y / N ");

            //record user input as first character
            char a = input.next().charAt(0);

            // if yes, update the records, and return the new account holder object (ID key stays the same)
            if (a =='Y'){
                Scanner input2 = new Scanner(System.in);
                System.out.println("Please insert the following details:");
                System.out.println("First Name: ");
                accountHolder.setName(input2.nextLine()); //add user input as account holder name
                System.out.println("Surname: ");
                accountHolder.setSurname(input2.nextLine()); //add user input as account holder surname
                accountHolder.setPhotoId(true);
                accountHolder.setProofOfAddress(true);
                System.out.println("Address: ");
                accountHolder.setAddress(input2.nextLine()); //add user input as account holder address
                System.out.println("Postcode: ");
                accountHolder.setPostcode(input2.nextLine()); //add user input as account holder post code
                System.out.println("Contact number: ");
                accountHolder.setPhoneNumber(input2.nextLong()); //add user input as account holder phone number
                System.out.println("Email: ");
                accountHolder.setEmail(input2.nextLine()); //add user input as account holder email

                //display new account holder stored details
                System.out.println("Done. Here are the new details for ID " + accountHolder.getId() + ": "
                        + "\n" + "Full name: " + accountHolder.name + " " + accountHolder.surname
                        + "\n"+ "Date of birth: " + accountHolder.getDateOfBirth()
                        + "\n" + "Address: " + accountHolder.getAddress() + ", Postcode: " + accountHolder.getPostcode()
                        +"\n" + "Contact number: " +accountHolder.getPhoneNumber()
                        +"\n" + "Account(s) records: " +accountHolder.getAccount());

                System.out.println("Returning to Main Menu...");

                //return accountHolder object
                return accountHolder;
            }
            //if the user does not wish to update details, return to main menu
            else{
                System.out.println("Returning to Main Menu...");
                //return to main menu
                Main.menu();
            }
        }
        return accountHolder;
    }

    //method to remove an account holder , using the ID key
    public void removeAccountHolder (Scanner input, Map<Integer,AccountHolder> customers) {
    }
}
