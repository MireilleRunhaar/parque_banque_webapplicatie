package nl.team2.parque_banque_server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public abstract class User {

    @Id
    @GeneratedValue
    protected long id;

    protected String surName;
    protected String firstName;
    protected String affix;
    protected String phoneNumber;
    protected String eMailAddress;

    @ManyToOne
    protected Address address;

    public User() {
        super();
    }

    public User(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address) {
        this.surName = surName;
        this.firstName = firstName;
        this.affix = affix;
        this.phoneNumber = phoneNumber;
        this.eMailAddress = eMailAddress;
        this.address = address;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", affix='" + affix + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMailAddress='" + eMailAddress + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getSurName().equals(user.getSurName()) &&
                getFirstName().equals(user.getFirstName()) &&
                Objects.equals(getAffix(), user.getAffix()) &&
                getPhoneNumber().equals(user.getPhoneNumber()) &&
                geteMailAddress().equals(user.geteMailAddress()) &&
                getAddress().equals(user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurName(), getFirstName(), getAffix(), getPhoneNumber(), geteMailAddress(), getAddress());
    }
}

