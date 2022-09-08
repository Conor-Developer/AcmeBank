import java.util.HashMap;
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

    protected Map<Integer, Account> account = new HashMap<>();
//    private Account account; //account(s) associated with the account holder

    private Scanner reader = new Scanner(System.in);

    //constructor
    public AccountHolder(int id, String name, String surname, String dateOfBirth, String address, String postCode, int phoneNumber, String email, boolean photoId, boolean proofOfAddress) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postcode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photoId = photoId;
        this.proofOfAddress = proofOfAddress;
    }

    protected void addAccount () {
        PersonalAccount newBankAccount = new PersonalAccount(2, 1, AccountTypes.Personal , false);
        account.put(8, newBankAccount);
    }

    public int findAccount (int accountNumber) {
        int correctId=0;
        for(Map.Entry<Integer, Account> values: account.entrySet()) {
            int AccountId = values.getValue().getAccNumber();
//                System.out.println( AccountId);
            if (AccountId == accountNumber) {
                correctId = values.getKey();
            }
        }
        return correctId;
    }
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

    public Map<Integer, Account> getAccount() {
        return account.getType();
    }

    //get and set public methods for accessing the object Account associated with the account holder
//    public Account getAccount() {
//        return account;
//    }
//    public void setAccount(Account account) {
//        this.account = account;
//    }

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
    public AccountHolder(int id) {
        this.id = id;
    }
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

        //if yes, set boolean photoId to being true§ (false by default)
        if (p=='Y'){
            accountHolder.setPhotoId(true);
        }
        //if not, the user will not be able to register a new customer
        else{
            System.out.println("Sorry, all account holders require a valid photo ID");
            //return to main menu
           // Main.menu();
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
           // Main.menu();
        }

        //set account holder ID ,
        // on the assumption that the teller will introduce an unique ID each time ( eg based on passport or NI number)
        System.out.println("Insert account holder unique ID: ");
        accountHolder.setId(input.nextInt());

        System.out.println("Done. " + accountHolder.name + " " + accountHolder.surname
                + " has the following ID: " + accountHolder.getId());


        //next, call method to create a bank account?

        System.out.println("Press Enter to return to Main Menu.");
        input.nextLine();
      //  Main.menu();

        return accountHolder; //return the accountHolder object created
    }

    //method to retrieve a customer record
    public void viewAccountHolder (){
        System.out.println("The account holder associated to the " + this.getId() + " ID is:" +"\n"  + "Full name: " + this.getName()
                        + " " + this.getSurname() + "\n"+ "Date of birth: " + this.getDateOfBirth() + "\n" + "Address: "
                        + this.getAddress() + "\n" + "Postcode: " + this.getPostcode() + "\n"
                        + "Contact number: " + this.getPhoneNumber() + "\n"
                        + "Email: " + this.getEmail() + "\n"
                        + "Account(s) :" + this.getAccount());
        }

    public AccountHolder updateAccountHolder(){
        System.out.println("The account holder associated to the " + this.getId() + " ID is:" + this.getName() + " " + this.getSurname());
        System.out.println( "Insert the following details to update the customer's details:");
        System.out.print("First Name: ");
        String firstName = reader.nextLine(); //add user input as account holder name
        this.setName(firstName);
        System.out.print("Surname: ");
        String surname = reader.nextLine(); //add user input as account holder surname
        this.setSurname(surname);
        System.out.print("Date of birth: ");
        String dob = reader.nextLine(); //add user input as account holder DOB
        this.setDateOfBirth(dob);
        System.out.print("Address: ");
        String address = reader.nextLine(); //add user input as account holder address
        this.setAddress(address);
        System.out.print("Post code: ");
        String postcode = reader.nextLine(); //add user input as account holder post code
        this.setPostcode(postcode);
        System.out.print("Contact number: ");
        int phoneNumber = reader.nextInt(); //add user input as account holder phone number
        this.setPhoneNumber(phoneNumber);
        System.out.print("Email: ");
        String email = reader.nextLine(); //add user input as account holder email
        this.setEmail(email);
        System.out.println();
        System.out.print("Customer Details Updated.");

        return this;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", photoId=" + photoId +
                ", proofOfAddress=" + proofOfAddress +
                ", account=" + account +
                '}';
    }



    //method to remove an account holder , using the ID key
    public void removeAccountHolder (Scanner input, Map<Integer,AccountHolder> customers) {

        // we search for the account holder by ID
        System.out.println("Enter the account holder's ID: ");

        //store the user input in a customerId variable
        Integer customerId = Integer.valueOf(input.nextLine());

        //instantiate an accountHolder object which would have the
        // use the customerId variable as key ID (Account Holder Map)
        AccountHolder accountHolder = customers.get(customerId);

        //if the map contains key display full name of the account holder and confirm request to delete data
        if (customers.containsKey(customerId)){
            System.out.println(" The account holder associated to the " + accountHolder.getId() + " ID is:"
                    +"\n"  + "Full name: " + accountHolder.getName() + " " + accountHolder.getSurname()
                    +"\n" + "Are you sure you want to delete this record? Y /N ");
            char a = input.next().charAt(0);
            // if user confirms, remove accountHolder object referring to this ID key
            if (a =='Y'){
                customers.remove(customerId);
                System.out.println("Account holder details have been deleted.");
            }
            //otherwise, stop the account deletion process and return to main menu
            else{
                System.out.println("Operation aborted. Returning to Main Menu...");
                //return to main menu
               // Main.menu();
            }

        }
        //if the map does not contain the specified key, return teller to main menu
        else {
            System.out.println("There is no account holder associated with this ID in the system.");
            System.out.println("Returning to the Main Menu...");
         //   Main.menu();
        }

    }
}
