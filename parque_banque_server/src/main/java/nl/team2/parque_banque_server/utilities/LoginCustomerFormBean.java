package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.PaymentAccount;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class LoginCustomerFormBean {

    private String bsn;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
    private List<PaymentAccount> paymentAccounts;

    public LoginCustomerFormBean() {
        super();
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
        return "LoginCustomerFormBean{" +
                "bsn='" + bsn + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", paymentAccounts=" + paymentAccounts +
                '}';
    }
}
