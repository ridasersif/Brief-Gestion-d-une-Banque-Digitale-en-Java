package classes;
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

    public Transaction(TransactionType transactionType, double amount, Account sourceAccount, Account destinationAccount) {
        this.transactionId = ++counter;
        this.transactionType = transactionType;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
