package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;

public class LoginCustomerFormBean {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public LoginCustomerFormBean() {
        super();
    }

    public LoginCustomerFormBean(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
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
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
