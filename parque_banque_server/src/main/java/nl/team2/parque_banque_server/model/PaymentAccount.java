package nl.team2.parque_banque_server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class PaymentAccount {

    @Id
    protected String iban;
    protected long balanceCent;

    @OneToMany(cascade = CascadeType.PERSIST)
    protected List<Transaction> transactionHistory;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    protected List<Customer> accountHolders;

    public PaymentAccount(){
    }

    public PaymentAccount(String iban, long balanceCent){
        this.iban = iban;
        this.balanceCent = balanceCent;
        this.accountHolders=new ArrayList<>();

    }

    public boolean validateSufficientBalance(long transactionAmount){
        return balanceCent >= transactionAmount;
    }

    public void addCustomerToAccountHolder(Customer customer){
        accountHolders.add(customer);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getBalance() {
        return balanceCent;
    }

    public void setBalance(long balance) {
        this.balanceCent = balanceCent;
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
                ", balance=" + balanceCent +
                ", transactionHistory=" + transactionHistory +
                ", accountHolders=" + accountHolders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentAccount that = (PaymentAccount) o;
        return balanceCent == that.balanceCent &&
                Objects.equals(iban, that.iban) &&
                Objects.equals(transactionHistory, that.transactionHistory) &&
                Objects.equals(accountHolders, that.accountHolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balanceCent, transactionHistory, accountHolders);
    }

    public long getBalanceCent() {
        return balanceCent;
    }

    public void setBalanceCent(long balanceCent) {
        this.balanceCent = balanceCent;
    }
}
