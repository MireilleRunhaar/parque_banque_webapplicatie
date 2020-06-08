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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    protected List<Customer> accountHolders;

    public PaymentAccount(){
    }

    public PaymentAccount(String iban, long balanceCent){
        this.iban = iban;
        this.balanceCent = balanceCent;
        this.accountHolders=new ArrayList<>();

    }

    public void withdraw(long transactionAmount){
        balanceCent = balanceCent - transactionAmount;
    }

    public void deposit(long transactionAmount){
        balanceCent = balanceCent + transactionAmount;
    }

    public boolean validateSufficientFunds(long transactionAmount){
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

    public long getBalanceCent() {
        return balanceCent;
    }

    public void setBalanceCent(long balanceCent) {
        this.balanceCent = balanceCent;
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
                ", balanceCent=" + balanceCent +
                ", accountHolders=" + accountHolders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentAccount)) return false;
        PaymentAccount that = (PaymentAccount) o;
        return getBalanceCent() == that.getBalanceCent() &&
                getIban().equals(that.getIban()) &&
                getAccountHolders().equals(that.getAccountHolders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIban(), getBalanceCent(), getAccountHolders());
    }
}
