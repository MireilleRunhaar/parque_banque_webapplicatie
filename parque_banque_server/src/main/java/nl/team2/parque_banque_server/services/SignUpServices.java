package nl.team2.parque_banque_server.services;

import nl.team2.parque_banque_server.model.Address;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpServices {

    private final static String NONCAP_DE = "de";
    private final static String NONCAP_DER = "der";
    private final static String NONCAP_VAN = "van";
    private final static String NONCAP_EN = "en";
    private final static String NONCAP_APOSTROPHE_S = "'s";

    public static SignUpFormBean formatFormInput(SignUpFormBean signUpFormBean) {
        signUpFormBean.setFirstName(StringUtils.capitalize(signUpFormBean.getFirstName()));
        signUpFormBean.setLastName(StringUtils.capitalize(signUpFormBean.getLastName()));

        // Split the Strings of street and city, and capitalize all relevant words
        String street = signUpFormBean.getStreet();
        List<String> streetParts = capitalizeStrings(street);
        street = String.join(" ", streetParts);
        signUpFormBean.setStreet(street);

        String city = signUpFormBean.getCity();
        List<String> cityParts = capitalizeStrings(city);
        city = String.join(" ", cityParts);
        signUpFormBean.setCity(city);

        signUpFormBean.setZipcode(signUpFormBean.getZipcode().toUpperCase());

        return signUpFormBean;
    }

    // Capitalize all words except specified words
    private static List<String> capitalizeStrings(String string) {
        List<String> stringParts = new ArrayList<>(Arrays.asList(string.trim().split("\\s")));
        for (int index = 0; index < stringParts.size(); index++) {
            if (!stringParts.get(index).equalsIgnoreCase(NONCAP_VAN) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_DE) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_DER) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_EN) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_APOSTROPHE_S)) {
                String streetCaps = StringUtils.capitalize(stringParts.remove(index));
                stringParts.add(index, streetCaps);
            }
        }
        return stringParts;
    }

    // TODO: save customer in database
    public static void saveNewCustomer(CreateLoginFormBean createLoginFormBean) {
        Address address = new Address(createLoginFormBean.getStreet(), createLoginFormBean.getNumber(),
                createLoginFormBean.getAddition(), createLoginFormBean.getZipcode(),
                createLoginFormBean.getCity());
        Customer customer = new Customer(createLoginFormBean.getLastName(), createLoginFormBean.getFirstName(),
                createLoginFormBean.getInfix(), createLoginFormBean.getPhone(), createLoginFormBean.getEmailAddress(),
                address);
        customer.setBsn(createLoginFormBean.getBsn());
        customer.setUserName(createLoginFormBean.getUsername());
        customer.setPassword(createLoginFormBean.getPassword());

        // TODO: remove sout
        System.out.println("CreateLoginController PostMapping: " + customer);

    }

    // Copy the contents of a SignUpFormBean to a CreateLoginFormBean
    public static CreateLoginFormBean copyToLoginFormBean(SignUpFormBean signUpFormBean) {
        CreateLoginFormBean createLoginFormBean = new CreateLoginFormBean();

        createLoginFormBean.setBsn(signUpFormBean.getBsn());
        createLoginFormBean.setFirstName(signUpFormBean.getFirstName());
        createLoginFormBean.setInfix(signUpFormBean.getInfix());
        createLoginFormBean.setLastName(signUpFormBean.getLastName());
        createLoginFormBean.setStreet(signUpFormBean.getStreet());
        createLoginFormBean.setNumber(signUpFormBean.getNumber());
        createLoginFormBean.setAddition(signUpFormBean.getAddition());
        createLoginFormBean.setZipcode(signUpFormBean.getZipcode());
        createLoginFormBean.setCity(signUpFormBean.getCity());
        createLoginFormBean.setPhone(signUpFormBean.getPhone());
        createLoginFormBean.setEmailAddress(signUpFormBean.getEmailAddress());

        return createLoginFormBean;
    }
}
