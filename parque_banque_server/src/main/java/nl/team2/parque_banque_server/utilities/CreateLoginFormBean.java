package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateLoginFormBean {

    @NotBlank
    @Size(min = 9, max = 9)
    private String bsn;

    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public CreateLoginFormBean() {
        super();
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "bsn='" + bsn + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
