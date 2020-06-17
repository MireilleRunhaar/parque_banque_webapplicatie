package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Address;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SignUpService {

    private final CustomerService customerService;

    private static final List<String> NONCAP_STRINGS = Arrays.asList("de", "der", "van", "en", "in", "tot", "des");

    private final static int ELF_PROEF_STARTING_FACTOR = 9;
    private final static int BSN_LENGTH = 9;
    private final static int ELFPROEF_DIVISOR = 11;

    @Autowired
    public SignUpService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public SignUpFormBean formatFormInput(SignUpFormBean signUpFormBean) {
        // Split the Strings of name, street and city, and capitalize all relevant parts
        String firstNameCaps = capitalizeStrings(signUpFormBean.getFirstName());
        signUpFormBean.setFirstName(firstNameCaps);

        String lastNameCaps = capitalizeStrings(signUpFormBean.getLastName());
        signUpFormBean.setLastName(lastNameCaps);

        String streetCaps = capitalizeStrings(signUpFormBean.getStreet());
        signUpFormBean.setStreet(streetCaps);

        String cityCaps = capitalizeStrings(signUpFormBean.getCity());
        signUpFormBean.setCity(cityCaps);

        // Remove possible space from input zipcode and capitalize string
        String formattedZipcode = String.join("", signUpFormBean.getZipcode().split("\\s")).toUpperCase();
        signUpFormBean.setZipcode(formattedZipcode);

        return signUpFormBean;
    }

    // Capitalize all Strings delimited by a space, except for specified Strings
    private String capitalizeStrings(String string) {
        string = capitalizeDashedName(string);
        List<String> stringParts = new ArrayList<>(Arrays.asList(string.trim().split("\\s")));
        String streetCaps = StringUtils.capitalize(stringParts.remove(0));
        stringParts.add(0, streetCaps);
        for (int index = 1; index < stringParts.size(); index++) {
            if (!NONCAP_STRINGS.contains(stringParts.get(index))) {
                streetCaps = StringUtils.capitalize(stringParts.remove(index));
                stringParts.add(index, streetCaps);
            }
        }
        return String.join(" ", stringParts);
    }

    // Capitalize all strings delimited by '-'
    private String capitalizeDashedName(String name) {
        List<String> nameParts = new ArrayList<>(Arrays.asList(name.trim().split("-")));
        String nameCaps = StringUtils.capitalize(nameParts.remove(0));
        nameParts.add(0, nameCaps);
        for (int index = 0; index < nameParts.size(); index++) {
            // Only capitalize the first word after a dash if it is not an excepted String
            String[] splitBySpace = nameParts.get(index).split("\\s");
            if (!NONCAP_STRINGS.contains(splitBySpace[0])) {
                nameCaps = StringUtils.capitalize(nameParts.remove(index));
                nameParts.add(index, nameCaps);
            }
        }
        return String.join("-", nameParts);
    }

    public void saveNewCustomer(SignUpFormBean signUpFormBean, CreateLoginFormBean createLoginFormBean) {
        Address address = new Address(signUpFormBean.getStreet(), signUpFormBean.getNumber(),
                signUpFormBean.getAddition(), signUpFormBean.getZipcode(),
                signUpFormBean.getCity());
        Customer customer = new Customer(signUpFormBean.getLastName(), signUpFormBean.getFirstName(),
                signUpFormBean.getInfix(), signUpFormBean.getPhone(), signUpFormBean.getEmailAddress(),
                address);
        customer.setBsn(signUpFormBean.getBsn());
        customer.setUserName(createLoginFormBean.getUsername());
        customer.setPassword(createLoginFormBean.getPassword());

        customerService.saveCustomer(customer);
    }

    // Tests whether a bsn passes the 'elfproef' test. The result of the calculation:
    // (9 × A) + (8 × B) + (7 × C) + (6 × D) + (5 × E) + (4 × F) + (3 × G) + (2 × H) + (-1 × I)
    // should be divisible by 11.
    public boolean passesElfproef(String bsn) {
        if (bsn.length() != BSN_LENGTH) return false;

        int calculation = 0;
        int factor = ELF_PROEF_STARTING_FACTOR;
        for (int index = 0; index < bsn.length(); index++) {
            if (index == bsn.length() - 1 ) {
                calculation += -factor * Integer.parseInt(bsn.substring(index, index + 1));
            } else {
                calculation += factor * Integer.parseInt(bsn.substring(index, index + 1));
                factor -= 1;
            }
        }

        return calculation % ELFPROEF_DIVISOR == 0 && calculation != 0;
    }

    public boolean isUserNameTaken(String username) {
        return customerService.findByUserName(username) != null;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}
