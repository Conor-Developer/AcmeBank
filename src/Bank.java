import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, AccountHolder> customerAccounts = new HashMap<Integer, AccountHolder>();

    public void addCustomerAccount(int id, AccountHolder customerAccount) {
        customerAccounts.put(id, customerAccount);
    }

    public Map<Integer, AccountHolder> getCustomerAccounts() {
        return customerAccounts;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "customerAccounts=" + customerAccounts +
                '}';
    }
}
