package nl.team2.parque_banque_server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class PrivateAccount extends PaymentAccount {

    public PrivateAccount() {
         }

    public PrivateAccount(String iban, long balanceCent) {
        super(iban, balanceCent);
    }

    @Override
    public String toString() {
        return "PrivateAccount{" +
                "iban='" + iban + '\'' +
                ", balanceCent=" + balanceCent +
                ", transactionHistory=" + transactionHistory +
                ", accountHolders=" + accountHolders +
                '}';
    }
}
