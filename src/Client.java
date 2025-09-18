import java.util.HashMap;
import java.util.Map;

public class Client extends User {
    private static int clientCounter = 0;
    private int clientId;
    private Map<Integer, Account> accounts;

    public Client(String lastName, String firstName, String email, String password) {
        super(lastName, firstName, email, password);
        this.clientId = ++clientCounter;
        this.accounts = new HashMap<>();
    }

    // Getters
    public int getClientId() {
        return clientId;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }


    public void addAccount(Account account){
        accounts.put(account.getAccountId(), account);
        System.out.println("Account created for client: " +  account.getAccountId());
    }


    public void removeAccount(int accountId){
        accounts.remove(accountId);
        if(accounts.containsKey(accountId)){
            accounts.remove(accountId);
            System.out.println("Account " + accountId + " has been removed successfully.");
        }else {
            System.out.println("Account " + accountId + " does not exist.");
        }
    }


    public Account getAccount(int accountId){
        return accounts.get(accountId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", accounts=" + accounts.keySet() +
                '}' + super.toString();
    }
}
