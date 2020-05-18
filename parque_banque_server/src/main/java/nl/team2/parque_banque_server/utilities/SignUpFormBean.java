package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpFormBean {

    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 10)
    private String infix;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    public SignUpFormBean() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
