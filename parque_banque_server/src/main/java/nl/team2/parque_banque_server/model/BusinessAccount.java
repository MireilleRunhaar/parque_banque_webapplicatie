package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BusinessAccount extends PaymentAccount{

    @ManyToOne
    private Employee accountmanager;

    @ManyToOne
    private Company company;

    public BusinessAccount() {
    }

    public BusinessAccount(String iban, long balanceCent) {
        super(iban, balanceCent);
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
}
