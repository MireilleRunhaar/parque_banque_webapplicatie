package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;

@Entity
public class PrivateAccount extends PaymentAccount {

    public PrivateAccount() {
    }

    public PrivateAccount(String iban, long balanceCent) {
        super(iban, balanceCent);
    }





}
