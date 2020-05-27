package nl.team2.parque_banque_server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User {

    private String bsn;
    private String userName;
    private String password;

    @ManyToMany(mappedBy = "accountHolders", cascade = CascadeType.ALL)
    private List<PaymentAccount> paymentAccounts;


    public Customer() {
    }

    public Customer(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
    }

    public Customer(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address, String bsn, String userName, String password) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
        this.bsn = bsn;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = new ArrayList<>();
    }

    public Customer(String bsn, String userName, String password) {
        this.bsn = bsn;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = new ArrayList<>();
    }

    public void addPaymentAccount(PaymentAccount paymentAccount) {
        paymentAccounts.add(paymentAccount);
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "bsn='" + bsn + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", paymentAccounts=" + paymentAccounts +
                ", id=" + id +
                ", surName='" + surName + '\'' +
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
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return getBsn().equals(customer.getBsn()) &&
                getUserName().equals(customer.getUserName()) &&
                getPassword().equals(customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBsn(), getUserName(), getPassword());
    }
}
