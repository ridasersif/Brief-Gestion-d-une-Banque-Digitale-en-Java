package classes;
import enums.AccountType;

public class BankManager extends User {
    private static int managerCounter = 0;
    private int employeedId;

    public BankManager(String lastName, String firstName, String email, String password) {
        super(lastName, firstName, email, password);
        this.employeedId = ++managerCounter;
    }

    public void createAccount(Client client, AccountType accountType, double initialBalance){
        Account newAccount = new Account(initialBalance, accountType);
        client.addAccount(newAccount);
    }

    public void closeAccount(Client client, int accountId){
        client.removeAccount(accountId);
    }

    public void updateClient(Client client, String newLastName, String newFirstName, String newEmail){
        client.setLastName(newLastName);
        client.setFirstName(newFirstName);
        client.setEmail(newEmail);
        System.out.println("Client information updated.");
    }

    @Override
    public String toString() {
        return "BankManager{" +
                "employeedId=" + employeedId +
                "} " + super.toString();
    }
}
