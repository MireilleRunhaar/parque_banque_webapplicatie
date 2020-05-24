package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountManager {

    @Id
    @GeneratedValue
    private int id;

    public AccountManager() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AccountManager{}";
    }

}
