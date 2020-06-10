package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Authorisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String securityCode;
    private String iban;

    public Authorisation() {
        this(null, null, null);
    }

    public Authorisation(String userName, String securityCode, String iban) {
        this.userName = userName;
        this.securityCode = securityCode;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Authorisation{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorisation that = (Authorisation) o;
        return id == that.id &&
                userName.equals(that.userName) &&
                securityCode.equals(that.securityCode) &&
                iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, securityCode, iban);
    }
}
