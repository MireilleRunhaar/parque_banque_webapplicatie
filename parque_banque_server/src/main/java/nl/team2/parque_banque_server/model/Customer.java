package nl.team2.parque_banque_server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer extends User {

    private String BSN;
    private String userName;
    private String password;
    private List<PaymentAccount> paymentAccounts;

    public Customer() {
        super();
    }

    public Customer(String BSN, String userName, String password) {
        this.BSN = BSN;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = new ArrayList<>();
    }

    public Customer(String name, String address, String zipcode, String phoneNumber, String eMailAdress, String BSN, String userName, String password) {
        super(name, address, zipcode, phoneNumber, eMailAdress);
        this.BSN = BSN;
        this.userName = userName;
        this.password = password;
        this.paymentAccounts = new ArrayList<>();
    }

    public String getBSN() {
        return BSN;
    }

    public void setBSN(String BSN) {
        this.BSN = BSN;
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

    public void addPaymentAccount (PaymentAccount paymentAccount) {
        if(paymentAccount != null) {
            this.paymentAccounts.add(paymentAccount);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "BSN='" + BSN + '\'' +
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
        return getBSN().equals(customer.getBSN()) &&
                getUserName().equals(customer.getUserName()) &&
                getPassword().equals(customer.getPassword()) &&
                Objects.equals(getPaymentAccounts(), customer.getPaymentAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBSN(), getUserName(), getPassword(), getPaymentAccounts());
    }
}
