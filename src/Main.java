import classes.*;
import enums.AccountType;
import java.util.Scanner;
import services.LoginSystem;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();


        BankManager manager = new BankManager("Admin", "Bank", "admin@gmail.com", "0000");
        loginSystem.registerUser(manager);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Bank System Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Email: ");
                    String loginEmail = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = sc.nextLine();

                    User loggedUser = loginSystem.login(loginEmail, loginPassword);
                    if (loggedUser != null) {


                        if (loggedUser instanceof Client) {
                            Client client = (Client) loggedUser;
                            boolean clientMenu = true;

                            while (clientMenu) {
                                System.out.println("\n--- Client Menu ---");
                                System.out.println("1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Transfer");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Logout");
                                System.out.print("Choose: ");
                                int cChoice = sc.nextInt();
                                sc.nextLine();

                                Account account = client.getFirstAccount();

                                switch (cChoice) {
                                    case 1:
                                        System.out.print("Amount to deposit: ");
                                        double dep = sc.nextDouble();
                                        sc.nextLine();
                                        account.deposit(dep);
                                        break;

                                    case 2:
                                        System.out.print("Amount to withdraw: ");
                                        double wit = sc.nextDouble();
                                        sc.nextLine();
                                        account.withdraw(wit);
                                        break;

                                    case 3:
                                        System.out.print("Destination account number: ");
                                        String destAccNum = sc.nextLine();
                                        System.out.print("Amount: ");
                                        double trAmount = sc.nextDouble();
                                        sc.nextLine();

                                        Account destAcc = loginSystem.getAccountByNumber(destAccNum);
                                        if (destAcc != null) {
                                            account.transfer(destAcc, trAmount);
                                        } else {
                                            System.out.println("Destination account not found!");
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Your accounts:");

                                        for (Account a : client.getAccounts().values()) {
                                            System.out.println(a);
                                        }
                                        break;

                                    case 5:
                                        clientMenu = false;
                                        break;

                                    default:
                                        System.out.println("Invalid option");
                                }
                            }


                        } else if (loggedUser instanceof BankManager) {
                            BankManager mgr = (BankManager) loggedUser;
                            boolean managerMenu = true;

                            while (managerMenu) {
                                System.out.println("\n--- BankManager Menu ---");
                                System.out.println("1. Create Client");
                                System.out.println("2. Create Account for existing Client");
                                System.out.println("3. Close Account");
                                System.out.println("4. Update Client Info");
                                System.out.println("5. Logout");
                                System.out.print("Choose: ");
                                int mChoice = sc.nextInt();
                                sc.nextLine();

                                switch (mChoice) {
                                    case 1:
                                        System.out.print("First Name: ");
                                        String fName = sc.nextLine();
                                        System.out.print("Last Name: ");
                                        String lName = sc.nextLine();
                                        System.out.print("Email: ");
                                        String em = sc.nextLine();
                                        System.out.print("Password: ");
                                        String pw = sc.nextLine();
                                        System.out.print("Initial balance: ");
                                        double balance = sc.nextDouble();
                                        sc.nextLine();
                                        System.out.print("Account type (1-SAVINGS, 2-CURRENT): ");
                                        int aType = sc.nextInt();
                                        sc.nextLine();

                                        AccountType accountType = (aType == 1) ? AccountType.SAVINGS : AccountType.CURRENT;
                                        Client newClient = new Client(lName, fName, em, pw, accountType, balance);
                                        loginSystem.registerUser(newClient);
                                        break;

                                    case 2:
                                        System.out.print("Client email: ");
                                        String clientEmail = sc.nextLine();
                                        Client existingClient = loginSystem.getClientByEmail(clientEmail);

                                        if (existingClient != null) {
                                            System.out.print("Initial balance: ");
                                            double b = sc.nextDouble();
                                            sc.nextLine();
                                            System.out.print("Account type (1-SAVINGS, 2-CURRENT): ");
                                            int t = sc.nextInt();
                                            sc.nextLine();

                                            AccountType typeAcc = (t == 1) ? AccountType.SAVINGS : AccountType.CURRENT;
                                            mgr.createAccount(existingClient, typeAcc, b);
                                            loginSystem.addAccountToSystem(existingClient.getFirstAccount());
                                        } else {
                                            System.out.println("Client not found!");
                                        }
                                        break;

                                    case 3:
                                        System.out.print("Client email: ");
                                        String cEmail = sc.nextLine();
                                        Client clientC = loginSystem.getClientByEmail(cEmail);

                                        if (clientC != null) {
                                            System.out.print("Account ID to close: ");
                                            int accId = sc.nextInt();
                                            sc.nextLine();
                                            mgr.closeAccount(clientC, accId);
                                        } else {
                                            System.out.println("Client not found!");
                                        }
                                        break;

                                    case 4:
                                        System.out.print("Client email: ");
                                        String uEmail = sc.nextLine();
                                        Client clientU = loginSystem.getClientByEmail(uEmail);

                                        if (clientU != null) {
                                            System.out.print("New First Name: ");
                                            String nf = sc.nextLine();
                                            System.out.print("New Last Name: ");
                                            String nl = sc.nextLine();
                                            System.out.print("New Email: ");
                                            String ne = sc.nextLine();
                                            mgr.updateClient(clientU, nl, nf, ne);
                                        } else {
                                            System.out.println("Client not found!");
                                        }
                                        break;

                                    case 5:
                                        managerMenu = false;
                                        break;

                                    default:
                                        System.out.println("Invalid option");
                                }
                            }
                        }
                    }
                    break;

                case 2:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

        sc.close();
    }
}
