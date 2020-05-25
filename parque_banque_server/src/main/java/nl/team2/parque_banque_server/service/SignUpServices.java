package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Address;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SignUpServices {

    private final static String NONCAP_DE = "de";
    private final static String NONCAP_DER = "der";
    private final static String NONCAP_VAN = "van";
    private final static String NONCAP_EN = "en";
    private final static String NONCAP_APOSTROPHE_S = "'s";

    public static SignUpFormBean formatFormInput(SignUpFormBean signUpFormBean) {
        // TODO: split names based on '-' and capitalize both names?
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
    public static void saveNewCustomer(SignUpFormBean signUpFormBean, CreateLoginFormBean createLoginFormBean) {
        Address address = new Address(signUpFormBean.getStreet(), signUpFormBean.getNumber(),
                signUpFormBean.getAddition(), signUpFormBean.getZipcode(),
                signUpFormBean.getCity());
        Customer customer = new Customer(signUpFormBean.getLastName(), signUpFormBean.getFirstName(),
                signUpFormBean.getInfix(), signUpFormBean.getPhone(), signUpFormBean.getEmailAddress(),
                address);
        customer.setBsn(signUpFormBean.getBsn());
        customer.setUserName(createLoginFormBean.getUsername());
        customer.setPassword(createLoginFormBean.getPassword());

        // TODO: remove sout
        System.out.println("CreateLoginController PostMapping: " + customer);

    }

}
