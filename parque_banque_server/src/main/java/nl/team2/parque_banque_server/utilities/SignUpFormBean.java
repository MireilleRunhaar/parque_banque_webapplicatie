package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpFormBean {

    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;

    @Size(max = 10)
    private String infix;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 9, max = 9)
    private String bsn;

    @NotBlank
    @Size(min = 5, max = 30)
    private String street;

    @NotBlank
    @Min(1)
    private String number;

    @Size(max = 1)
    private String addition;

    @NotBlank
    @Size(min = 6, max = 6)
    private String postcode;

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

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "SignUpFormBean{" +
                "firstName='" + firstName + '\'' +
                ", infix='" + infix + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
