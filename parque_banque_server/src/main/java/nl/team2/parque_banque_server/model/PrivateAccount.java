package nl.team2.parque_banque_server.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PrivateAccount extends PaymentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public PrivateAccount(String iban, long balanceCent) {
        super(iban, balanceCent);
    }

    public PrivateAccount() {
    }

    @Override
    public String getIban() {
        return super.getIban();
    }

    @Override
    public void setIban(String iban) {
        super.setIban(iban);
    }

    @Override
    public long getBalance() {
        return super.getBalance();
    }

    @Override
    public void setBalance(long balance) {
        super.setBalance(balance);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "PrivateAccount{}";
    }

}
