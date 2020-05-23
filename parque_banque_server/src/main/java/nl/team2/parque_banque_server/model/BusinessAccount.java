package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BusinessAccount extends PaymentAccount {

    @Id
    @GeneratedValue
    private int id;

    private Company company;
    private AccountManager accountManager;

    public BusinessAccount() {
        this(null, null);
    }

    public BusinessAccount(Company company, AccountManager accountManager) {
        this.company = company;
        this.accountManager = accountManager;
    }

    public BusinessAccount(String iban, long balanceCent, Company company, AccountManager accountManager) {
        super(iban, balanceCent);
        this.id=0;
        this.company = company;
        this.accountManager = accountManager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
}
