package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.*;

public class SignUpFormBean {

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[- 'A-Za-zÀ-ÖØ-öø-ÿ]{2,30}$")
    private String firstName;

    @Size(max = 10)
    @Pattern(regexp = "$|^[- 'A-Za-zÀ-ÖØ-öø-ÿ]{1,10}")
    private String infix;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[- 'A-Za-zÀ-ÖØ-öø-ÿ]{2,50}$")
    private String lastName;

    @NotBlank
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[0-9]{9}")
    private String bsn;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[-.' A-Za-z]{2,50}")
    private String street;

    @NotBlank
    @Min(1)
    @Pattern(regexp = "[1-9][0-9]{0,4}")
    private String number;

    @Size(max = 1)
    @Pattern(regexp = "$|[1-9a-zA-Z]")
    private String addition;

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(regexp = "[1-9][0-9]{3}[A-Za-z]{2}")
    private String zipcode;

    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "[-' A-Za-z]{2,}")
    private String city;

    @Pattern(regexp = "$|[0][0-9]{9}")
    private String phone;

    @Email
    private String emailAddress;



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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public String toString() {
        return "SignUpFormBean{" +
                "firstName='" + firstName + '\'' +
                ", infix='" + infix + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bsn='" + bsn + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", addition='" + addition + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
