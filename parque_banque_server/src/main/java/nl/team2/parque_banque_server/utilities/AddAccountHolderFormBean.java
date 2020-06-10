package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddAccountHolderFormBean {

    @NotBlank
    @Size(min = 5, max = 255)
    private String username;

    @NotBlank
    @Pattern(regexp = "[0-9]{5}")
    private String securityCode;

    public AddAccountHolderFormBean() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "AddAccountHolderFormBean{" +
                "username='" + username + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
