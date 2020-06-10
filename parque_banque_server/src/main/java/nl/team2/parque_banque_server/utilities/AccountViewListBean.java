package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;

public class AccountViewListBean {

    @NotBlank
    private String iban;

    @NotBlank
    private String balanceEuros;

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
}
