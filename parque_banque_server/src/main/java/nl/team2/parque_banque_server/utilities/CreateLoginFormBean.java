package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.*;

public class CreateLoginFormBean {

    @NotBlank
    @Size(min = 5, max = 50)
    @Pattern(regexp = "[A-Za-z0-9]{5,}")
    private String username;

    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%?&]{8,}")
    private String password;


    public CreateLoginFormBean() {
        super();
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
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
