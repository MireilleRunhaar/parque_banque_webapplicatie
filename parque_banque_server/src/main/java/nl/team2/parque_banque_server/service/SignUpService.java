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
public class SignUpService {

    private final static String NONCAP_DE = "de";
    private final static String NONCAP_DER = "der";
    private final static String NONCAP_VAN = "van";
    private final static String NONCAP_EN = "en";
    private final static String NONCAP_IN = "in";
    private final static String NONCAP_TOT = "tot";

    public static SignUpFormBean formatFormInput(SignUpFormBean signUpFormBean) {
        // Split the Strings of name, street and city, and capitalize all relevant parts
        String firstNameCaps = capitalizeStrings(signUpFormBean.getFirstName());
        signUpFormBean.setFirstName(firstNameCaps);

        String lastNameCaps = capitalizeStrings(signUpFormBean.getLastName());
        signUpFormBean.setLastName(lastNameCaps);

        String streetCaps = capitalizeStrings(signUpFormBean.getStreet());
        signUpFormBean.setStreet(streetCaps);

        String cityCaps = capitalizeStrings(signUpFormBean.getCity());
        signUpFormBean.setCity(cityCaps);


        signUpFormBean.setZipcode(signUpFormBean.getZipcode().toUpperCase());

        return signUpFormBean;
    }

    // Capitalize all Strings delimited by a space, except for specified Strings
    private static String capitalizeStrings(String string) {
        string = capitalizeDashedName(string);
        List<String> stringParts = new ArrayList<>(Arrays.asList(string.trim().split("\\s")));
        String streetCaps = StringUtils.capitalize(stringParts.remove(0));
        stringParts.add(0, streetCaps);
        for (int index = 1; index < stringParts.size(); index++) {
            if (!stringParts.get(index).equalsIgnoreCase(NONCAP_VAN) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_DE) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_DER) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_EN) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_IN) &&
                    !stringParts.get(index).equalsIgnoreCase(NONCAP_TOT)) {
                streetCaps = StringUtils.capitalize(stringParts.remove(index));
                stringParts.add(index, streetCaps);
            }
        }
        return String.join(" ", stringParts);
    }

    // Capitalize all strings delimited by '-'
    private static String capitalizeDashedName(String name) {
        List<String> nameParts = new ArrayList<>(Arrays.asList(name.trim().split("-")));
        String nameCaps = StringUtils.capitalize(nameParts.remove(0));
        nameParts.add(0, nameCaps);
        for (int index = 0; index < nameParts.size(); index++) {
            if (!nameParts.get(index).startsWith(NONCAP_VAN + " ") &&
                    !nameParts.get(index).startsWith(NONCAP_DE + " ") &&
                    !nameParts.get(index).startsWith(NONCAP_DER + " ") &&
                    !nameParts.get(index).startsWith(NONCAP_EN + " ") &&
                    !nameParts.get(index).startsWith(NONCAP_IN + " ") &&
                    !nameParts.get(index).startsWith(NONCAP_TOT + " ")) {
                nameCaps = StringUtils.capitalize(nameParts.remove(index));
                nameParts.add(index, nameCaps);
            }
        }
        return String.join("-", nameParts);
    }

    public static Customer createNewCustomer(SignUpFormBean signUpFormBean, CreateLoginFormBean createLoginFormBean) {
        Address address = new Address(signUpFormBean.getStreet(), signUpFormBean.getNumber(),
                signUpFormBean.getAddition(), signUpFormBean.getZipcode(),
                signUpFormBean.getCity());
        Customer customer = new Customer(signUpFormBean.getLastName(), signUpFormBean.getFirstName(),
                signUpFormBean.getInfix(), signUpFormBean.getPhone(), signUpFormBean.getEmailAddress(),
                address);
        customer.setBsn(signUpFormBean.getBsn());
        customer.setUserName(createLoginFormBean.getUsername());
        customer.setPassword(createLoginFormBean.getPassword());

        return customer;
    }

}
