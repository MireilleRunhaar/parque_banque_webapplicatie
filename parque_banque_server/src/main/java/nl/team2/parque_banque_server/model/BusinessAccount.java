package nl.team2.parque_banque_server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class BusinessAccount extends PaymentAccount{

    @ManyToOne
    private Employee accountmanager;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Company company;

    public BusinessAccount() {
    }

    public BusinessAccount(String iban, long balanceCent, Employee accountmanager, Company company) {
        super(iban, balanceCent);
        this.accountmanager = accountmanager;
        this.company = company;
    }

    public Employee getAccountmanager() {
        return accountmanager;
    }

    public void setAccountmanager(Employee accountmanager) {
        this.accountmanager = accountmanager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "BusinessAccount{" +
                "accountmanager=" + accountmanager +
                ", company=" + company +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessAccount)) return false;
        if (!super.equals(o)) return false;
        BusinessAccount that = (BusinessAccount) o;
        return getAccountmanager().equals(that.getAccountmanager()) &&
                getCompany().equals(that.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccountmanager(), getCompany());
    }
}
