import java.util.HashMap;
import java.util.Map;

// The Bank class stores the hashmap of AccountHolders in customerAccounts
public class Bank {
    private final Map<Integer, AccountHolder> customerAccounts = new HashMap<>();

    // Add a new Account holder to the hashmap.
    public void addCustomerAccount(int id, AccountHolder customerAccount) {
        customerAccounts.put(id, customerAccount);
    }

    // Remove an account holder from the hashmap.
    public void removeCustomerAccount(int id) {
        customerAccounts.remove(id);
    }

    // Getter
    public Map<Integer, AccountHolder> getCustomerAccounts() {
        return customerAccounts;
    }

    // Find an account holder within the hashmap with their unique customerID
    protected int findCustomer(int customerID) {
        int correctId = 0;
        for (Map.Entry<Integer, AccountHolder> values : customerAccounts.entrySet()) {
            int AccountId = values.getValue().getId();
            if (AccountId == customerID) {
                correctId = values.getKey();
            }
        }
        return correctId;
    }

    /*
     * The bank class has the Account holder hashmap.
     * Nested Inside the account holder is hashmap is a hashmap that stores all the
     * bank accounts created by the account holder.
     * This method takes an accountNumber as an input and finds the bank accounts
     * stored within the nested hashmap
     */
    protected int findBankAccount(int accountNumber) {
        int correctBankAccountNumber = 0;

        for (Map.Entry<Integer, AccountHolder> customerAccount : customerAccounts.entrySet()) {
            for (Map.Entry<Integer, Account> bankAccount : customerAccount.getValue().getAccount().entrySet()) {
                int bankAccountId = bankAccount.getValue().getAccNumber();
                if (bankAccountId == accountNumber) {
                    correctBankAccountNumber = bankAccountId;
                }
            }
        }
        return correctBankAccountNumber;
    }

    // Finds the account holder id with the bank account number input
    protected int findAccountHolderId(int bankAccountNumber) {
        int correctAccountNumber = 0;

        for (Map.Entry<Integer, AccountHolder> customerAccount : customerAccounts.entrySet()) {
            for (Map.Entry<Integer, Account> bankAccount : customerAccount.getValue().getAccount().entrySet()) {
                int customerAccountId = bankAccount.getValue().getAccNumber();
                if (customerAccountId == bankAccountNumber) {
                    correctAccountNumber = customerAccount.getValue().getId();
                    break;
                }
            }
        }
        return correctAccountNumber;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "customerAccounts=" + customerAccounts +
                '}';
    }
}
