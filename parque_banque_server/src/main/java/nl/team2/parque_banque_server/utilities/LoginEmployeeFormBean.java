package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class LoginEmployeeFormBean {

    @Min(1)
    private int employeeNumber;

    @NotBlank
    private String password;

    public LoginEmployeeFormBean() {
        super();
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateLoginFormBean{" +
                "username='" + employeeNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
