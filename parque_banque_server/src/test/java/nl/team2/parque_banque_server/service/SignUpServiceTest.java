package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpServiceTest {

    private final String STARTING_FIRSTNAME = "jan-willem hendrik";
    private final String EXPECTED_FIRSTNAME = "Jan-Willem Hendrik";
    private final String STARTING_LASTNAME = "voorst tot voorst-de bruijn-in 't gooij";
    private final String EXPECTED_LASTNAME = "Voorst tot Voorst-de Bruijn-in 't Gooij";
    private final String STARTING_STREET = "burg. j. van 't hooft-van damstraat";
    private final String EXPECTED_STREET = "Burg. J. van 't Hooft-van Damstraat";
    private final String STARTING_ZIPCODE = "1234ab";
    private final String EXPECTED_ZIPCODE = "1234AB";
    private final String STARTING_CITY = "'s hertogenbosch-ter apel";
    private final String EXPECTED_CITY = "'s Hertogenbosch-Ter Apel";


    @Test
    void CapitalizeStringsTest() {
        SignUpFormBean input = new SignUpFormBean();
        input.setFirstName(STARTING_FIRSTNAME);
        input.setLastName(STARTING_LASTNAME);
        input.setStreet(STARTING_STREET);
        input.setZipcode(STARTING_ZIPCODE);
        input.setCity(STARTING_CITY);

        SignUpFormBean actual = SignUpService.formatFormInput(input);

        assertEquals(actual.getFirstName(), EXPECTED_FIRSTNAME);
        assertEquals(actual.getLastName(), EXPECTED_LASTNAME);
        assertEquals(actual.getStreet(), EXPECTED_STREET);
        assertEquals(actual.getZipcode(), EXPECTED_ZIPCODE);
        assertEquals(actual.getCity(), EXPECTED_CITY);

    }
}
