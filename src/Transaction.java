import enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private static int counter = 0;
    private int transactionId;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime date;
    private Account sourceAccount;
    private Account destinationAccount;

    //
    public Transaction(TransactionType transactionType, double amount, Account sourceAccount, Account destinationAccount) {
        this.transactionId = ++counter;  // auto-increment ID
        this.transactionType = transactionType;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.date = LocalDateTime.now(); // current date and time
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}