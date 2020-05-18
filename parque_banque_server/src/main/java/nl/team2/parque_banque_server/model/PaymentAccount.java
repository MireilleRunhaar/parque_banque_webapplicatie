package nl.team2.parque_banque_server.model;

import java.util.List;

public abstract class PaymentAccount {

    private String iban;
    private long balance;
    private List <Transaction> transactionHistory;
    private List<Customer> accountHolders;

    public PaymentAccount(String iban, long balance){
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                ", accountHolders=" + accountHolders +
                '}';
    }
}
