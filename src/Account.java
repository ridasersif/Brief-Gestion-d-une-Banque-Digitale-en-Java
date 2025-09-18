import enums.AccountType;
import enums.TransactionType;

import java.util.Random;
import java.util.HashSet;

public class Account {
    private static int accountCounter = 0;
    private int accountId;
    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private HashSet<Transaction> transactionHistory;


    public Account(double balance, AccountType accountType) {
        this.accountId = ++accountCounter;
        this.accountNumber = generateRIB();
        this.balance = balance;
        this.accountType = accountType;
        this.transactionHistory = new HashSet<>();
    }

    private String generateRIB() {
        Random random = new Random();
        StringBuilder rib = new StringBuilder();

        for (int i = 0; i < 24; i++) {
            rib.append(random.nextInt(10));
        }

        return rib.toString();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public HashSet<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(HashSet<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
    public void deposit(double amount) {
        if(amount>0){
            this.balance += amount;
            Transaction transaction = new Transaction(TransactionType.DEPOSIT,amount,null,this);
            transactionHistory.add(transaction);
            System.out.println("Deposit successful. New balance: " + this.balance);
        }else {
            System.out.println("Invalid deposit amount");
        }
    }
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount: amount must be positive");
            return false;
        } else if (amount > this.balance) {
            System.out.println("Insufficient balance");
            return false;
        } else {
            this.balance -= amount;
            Transaction transaction = new Transaction(TransactionType.WITHDRAWL,amount,this,null);
            transactionHistory.add(transaction);
            System.out.println("Withdrawal successful. New balance: " + this.balance);

            return true;
        }
    }


    public boolean transfer(Account toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            Transaction transaction = new Transaction(TransactionType.TRANSFER,amount,this,toAccount);
            this.transactionHistory.add(transaction);
            toAccount.getTransactionHistory().add(transaction);
            System.out.println("Transfer successful to account " + toAccount.getAccountNumber());
            System.out.println("Transfer successful. New balance: " + this.balance);
            return true;
        }else {
            System.out.println("Transfer failed");
            return false;
        }
    }


}
