package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountManager {

    @Id
    private int id;

    public AccountManager() {
        this.id = 0;
    }

    @Override
    public String toString() {
        return "AccountManager{}";
    }
}
