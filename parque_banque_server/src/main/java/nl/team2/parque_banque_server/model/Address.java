package nl.team2.parque_banque_server.model;

import java.util.Objects;

public class Address {

    private String street;
    private String number;
    private String addition;
    private String zipCode;
    private String city;

    public Address() {
        super();
    }

    public Address(String street, String number, String addition, String zipCode, String city) {
        this.street = street;
        this.number = number;
        this.addition = addition;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", addition='" + addition + '\'' +
                ", zippcode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getStreet().equals(address.getStreet()) &&
                getNumber().equals(address.getNumber()) &&
                Objects.equals(getAddition(), address.getAddition()) &&
                getZipCode().equals(address.getZipCode()) &&
                getCity().equals(address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getNumber(), getAddition(), getZipCode(), getCity());
    }
}
