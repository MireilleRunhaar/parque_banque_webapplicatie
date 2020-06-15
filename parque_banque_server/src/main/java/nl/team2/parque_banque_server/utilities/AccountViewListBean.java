package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;

public class AccountViewListBean {

    @NotBlank
    private String iban;

    @NotBlank
    private String balanceEuros;

    @NotBlank
    private String accountholders;

    private String businessName;

    public AccountViewListBean() {
        super();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBalanceEuros() {
        return balanceEuros;
    }

    public void setBalanceEuros(String balanceEuros) {
        this.balanceEuros = balanceEuros;
    }

    public String getAccountholders() {
        return accountholders;
    }

    public void setAccountholders(String accountholders) {
        this.accountholders = accountholders;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
