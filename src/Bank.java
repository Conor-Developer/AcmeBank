import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Integer, AccountHolder> customerAccounts = new HashMap<>();

    public void addCustomerAccount(int id, AccountHolder customerAccount) {
        customerAccounts.put(id, customerAccount);
    }

    public void removeCustomerAccount(int id) {
        customerAccounts.remove(id);
    }

    public Map<Integer, AccountHolder> getCustomerAccounts() {
        return customerAccounts;
    }

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
