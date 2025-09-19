package services;
import classes.*;
import java.util.HashMap;
import java.util.Map;

public class LoginSystem {
    private Map<String, User> users;
    private Map<String, Client> clientsByEmail;
    private Map<String, Account> accountsByNumber;

    public LoginSystem(){
        users = new HashMap<>();
        clientsByEmail = new HashMap<>();
        accountsByNumber = new HashMap<>();
    }

    public void registerUser(User user){
        if(users.containsKey(user.getEmail())){
            System.out.println("User already exists: " + user.getEmail());
        } else {
            users.put(user.getEmail(), user);
            System.out.println("User registered successfully: " + user.getEmail());
            if(user instanceof Client){
                Client client = (Client) user;
                clientsByEmail.put(client.getEmail(), client);
                client.getAccounts().values().forEach(acc -> accountsByNumber.put(acc.getAccountNumber(), acc));
            }
        }
    }

    public User login(String email, String password){
        if(!users.containsKey(email)){
            System.out.println("No user found with this email");
            return null;
        }
        User user = users.get(email);
        if(user.getPassword().equals(password)){
            System.out.println("Login successful: " + email);
            return user;
        } else {
            System.out.println("Invalid password");
            return null;
        }
    }

    public Client getClientByEmail(String email){
        return clientsByEmail.get(email);
    }

    public Account getAccountByNumber(String accNumber){
        return accountsByNumber.get(accNumber);
    }

    public void addAccountToSystem(Account account){
        accountsByNumber.put(account.getAccountNumber(), account);
    }
}
