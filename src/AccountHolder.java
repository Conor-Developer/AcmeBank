import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountHolder {

    private static int nextId = 4000;
    private final int id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String address;
    private String postcode;
    private String phoneNumber;
    private String email;

    private final boolean photoId;

    private final boolean proofOfAddress;

    //Hashmap of Bank accounts
    private final Map<Integer, Account> account = new HashMap<>();


    private final Scanner reader = new Scanner(System.in);


    public AccountHolder(String name, String surname, String dateOfBirth, String address, String postCode, String phoneNumber, String email, boolean photoId, boolean proofOfAddress) {
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
        } else if (type == AccountTypes.ISA) {
            newAccount = new ISAAccount(1, type, false);
            account.put(newAccount.getAccNumber(), newAccount);
        } else {
            newAccount = new BusinessAccount(7, type , false);
            account.put(newAccount.getAccNumber(), newAccount);
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Integer, Account> getAccount() {
        return account;
    }

    public void getAccountInfo() {
        AccountTypes accountInfo;
        int accountNumber;
        System.out.println("Accounts: ");
        for(Map.Entry<Integer, Account> values: account.entrySet()) {
            accountInfo =  values.getValue().getType();
            accountNumber = values.getValue().getAccNumber();
            System.out.println(accountInfo + " Account Number: " + accountNumber);
        }
        System.out.println();
    }

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
        String firstName = reader.nextLine();
        this.setName(firstName);
        System.out.print("Surname: ");
        String surname = reader.nextLine();
        this.setSurname(surname);
        System.out.print("Date of birth: ");
        String dob = reader.nextLine();
        this.setDateOfBirth(dob);
        System.out.print("Address: ");
        String address = reader.nextLine();
        this.setAddress(address);
        System.out.print("Post code: ");
        String postcode = reader.nextLine();
        this.setPostcode(postcode);
        System.out.print("Email: ");
        String newEmail = reader.nextLine();
        this.setEmail(newEmail);
        System.out.print("Contact number: ");
        String phoneNumber = reader.nextLine();
        this.setPhoneNumber(phoneNumber);
        System.out.print("Customer Details Updated.");
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
