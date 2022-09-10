import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountHolder {

// declare private variables - encapsulation

    private static int nextId = 4000;
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
    private Map<Integer, Account> account = new HashMap<>();
//    private Account account; //account(s) associated with the account holder

    private Scanner reader = new Scanner(System.in);

    //constructor
    public AccountHolder(String name, String surname, String dateOfBirth, String address, String postCode, int phoneNumber, String email, boolean photoId, boolean proofOfAddress) {
        this.id = nextId;
        nextId++;
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

    protected void addAccount (AccountTypes type) {
        Account newAccount;
        if (type == AccountTypes.Personal) {
            newAccount = new PersonalAccount(1, type , false);
            account.put(newAccount.getAccNumber(), newAccount);
            System.out.println(newAccount.toString());
        } else if (type == AccountTypes.ISA) {
            newAccount = new ISAAccount(1, type, false);
            account.put(newAccount.getAccNumber(), newAccount);
        } else {
            newAccount = new BusinessAccount(7, type , false);
            account.put(newAccount.getAccNumber(), newAccount);
        }
        System.out.println(newAccount.getType() + " account created successfully. ID is " + newAccount.getAccNumber());
    }


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
        return account;
    }

    public void getAccountInfo() {
        AccountTypes accountInfo;
        int accounNumber;
        System.out.println("Accounts: ");
        for(Map.Entry<Integer, Account> values: account.entrySet()) {
            accountInfo =  values.getValue().getType();
            accounNumber = values.getValue().getAccNumber();
            System.out.println(accountInfo + " Account Number: " + accounNumber);
        }
        System.out.println();
    }

    //method to retrieve a customer record
    public void viewAccountHolder (){
        System.out.println("The account holder associated to the " + this.getId() + " ID is:" +"\n"  + "Full name: " + this.getName()
                        + " " + this.getSurname() + "\n"+ "Date of birth: " + this.getDateOfBirth() + "\n" + "Address: "
                        + this.getAddress() + "\n" + "Postcode: " + this.getPostcode() + "\n"
                        + "Contact number: " + this.getPhoneNumber() + "\n"
                        + "Email: " + this.getEmail() + "\n"
                        );
        this.getAccountInfo();
        }

    public void updateAccountHolder(){
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
        System.out.print("Email: ");
        String newEmail = reader.nextLine(); //add user input as account holder email
        this.setEmail(newEmail);
        System.out.print("Contact number: ");
        int phoneNumber = reader.nextInt(); //add user input as account holder phone number
        this.setPhoneNumber(phoneNumber);
      //  System.out.println();
        System.out.print("Customer Details Updated.");

//        return this;
    }

    protected void removeCustomerAccount(int id) {
        account.remove(id);
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

}
