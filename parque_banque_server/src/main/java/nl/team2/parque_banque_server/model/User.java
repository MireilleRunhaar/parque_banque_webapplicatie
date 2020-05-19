package nl.team2.parque_banque_server.model;

import java.util.Objects;

public abstract class User {

    protected String name;
    protected String address;
    protected String zipcode;
    protected String phoneNumber;
    protected String eMailAdress;

    public User() {
        super();
    }

    public User(String name, String address, String zipcode, String phoneNumber, String eMailAdress) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.eMailAdress = eMailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMailAdress() {
        return eMailAdress;
    }

    public void seteMailAdress(String eMailAdress) {
        this.eMailAdress = eMailAdress;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMailAdress='" + eMailAdress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getName().equals(user.getName()) &&
                getAddress().equals(user.getAddress()) &&
                getZipcode().equals(user.getZipcode()) &&
                getPhoneNumber().equals(user.getPhoneNumber()) &&
                geteMailAdress().equals(user.geteMailAdress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getZipcode(), getPhoneNumber(), geteMailAdress());
    }
}
