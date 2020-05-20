package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User {

    @Id
    private String bsn;
    private String userName;
    private String password;

    @OneToMany
    private List<PaymentAccount> paymentAccounts;

    public Customer() {
        super();
    }

    public Customer(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
    }

    public Customer(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address, String bsn, String userName, String password, List<PaymentAccount> paymentAccounts) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
        this.bsn = bsn;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = paymentAccounts;
    }

    public Customer(String bsn, String userName, String password, List<PaymentAccount> paymentAccounts) {
        this.bsn = bsn;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = paymentAccounts;
    }

    public void addPaymentAccount(PaymentAccount paymentAccount){
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

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "BSN='" + bsn + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", paymentAccounts=" + paymentAccounts +
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
                getPassword().equals(customer.getPassword()) &&
                Objects.equals(getPaymentAccounts(), customer.getPaymentAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBsn(), getUserName(), getPassword(), getPaymentAccounts());
    }
}