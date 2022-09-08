import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, AccountHolder> customerAccounts = new HashMap<Integer, AccountHolder>();

    public void addCustomerAccount(int id, AccountHolder customerAccount) {
        customerAccounts.put(id, customerAccount);
    }
    public void removeCustomerAccount(int id) {
        customerAccounts.remove(id);
    }

    public Map<Integer, AccountHolder> getCustomerAccounts() {
        return customerAccounts;
    }

    protected int findCustomer (int customerID) {
        int correctId = 0;
        for(Map.Entry<Integer, AccountHolder> values: customerAccounts.entrySet()) {
            int AccountId = values.getValue().getId();
            if (AccountId == customerID) {
                correctId = values.getKey();
            }
        }
        return correctId;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "customerAccounts=" + customerAccounts +
                '}';
    }
}
