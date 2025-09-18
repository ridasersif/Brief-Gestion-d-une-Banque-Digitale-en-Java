import enums.AccountType;

public class BankManager extends User {
    private String employeedId;

    public BankManager(String lastName, String firstName, String email, String password, String employeedId) {
        super(lastName, firstName, email, password);
        this.employeedId = employeedId;
    }

    public void createAccount(Client client, AccountType accountType,double initialBalance) {
        if(initialBalance>=0){
            Account newAccount = new Account(initialBalance, accountType);
            client.addAccount(newAccount);

        }
    }
    public void closeAccount(Client client, int accountId) {
        client.removeAccount(accountId);
    }
    public void updateClient(Client client, String newLastName, String newFirstName, String newEmail) {
        client.setLastName(newLastName);
        client.setFirstName(newFirstName);
        client.setEmail(newEmail);
        System.out.println("Client information updated.");
    }
}
