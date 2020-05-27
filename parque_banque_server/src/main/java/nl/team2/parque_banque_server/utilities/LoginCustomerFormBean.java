package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.PaymentAccount;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class LoginCustomerFormBean {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public LoginCustomerFormBean() {
        super();
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

    @Override
    public String toString() {
        return "LoginCustomerFormBean{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
