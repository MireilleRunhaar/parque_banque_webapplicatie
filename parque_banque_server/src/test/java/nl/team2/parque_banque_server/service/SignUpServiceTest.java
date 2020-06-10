package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpServiceTest {

    private final static String PASSWORD = "M0ck-password";
    private final static String BSN = "123456782";

    @MockBean
    CustomerService customerService = Mockito.mock(CustomerService.class);

    SignUpService signUpService = new SignUpService(customerService);

    @BeforeEach
    public void testSetup() {
        Mockito.when(customerService.findByUserName("lisak")).thenReturn(null);
        Mockito.when(customerService.findByUserName("machiel")).thenReturn(null);
        Mockito.when(customerService.findByUserName("minke")).thenReturn(null);
        Mockito.when(customerService.findByUserName("mireille")).thenReturn(new Customer(BSN, "mireille", PASSWORD));
        Mockito.when(customerService.findByUserName("moraad")).thenReturn(new Customer(BSN, "moraad", PASSWORD));
        Mockito.when(customerService.findByUserName("sanne")).thenReturn(new Customer(BSN, "sanne", PASSWORD));
    }


    @Test
    void CapitalizeStringsTest() {
        // Test with only dashes as delimiter
        SignUpFormBean input = new SignUpFormBean();

        input.setFirstName("jan-willem-hendrik");
        input.setLastName("voorst-tot-voorst-de-bruijn-in-'t-gooij");
        input.setStreet("burg.-j.-van-'t-hooft-van-damstraat");
        input.setZipcode("1234 ab"); // Except zipCode as only space is allowed in input form (but required in method)
        input.setCity("'s-hertogenbosch-ter-apel-des-gravenhage");

        SignUpFormBean actual = signUpService.formatFormInput(input);

        assertEquals("Jan-Willem-Hendrik", actual.getFirstName());
        assertEquals("Voorst-tot-Voorst-de-Bruijn-in-'t-Gooij", actual.getLastName());
        assertEquals("Burg.-J.-van-'t-Hooft-van-Damstraat", actual.getStreet());
        assertEquals("'s-Hertogenbosch-Ter-Apel-des-Gravenhage", actual.getCity());

        // Test with only spaces as delimiter
        input.setFirstName("jan willem hendrik");
        input.setLastName("voorst tot voorst de bruijn in 't gooij");
        input.setStreet("burg. j. van 't hooft van damstraat");
        input.setZipcode("1234 ab");
        input.setCity("'s hertogenbosch ter apel des gravenhage");

        actual = signUpService.formatFormInput(input);

        assertEquals("Jan Willem Hendrik", actual.getFirstName());
        assertEquals("Voorst tot Voorst de Bruijn in 't Gooij", actual.getLastName());
        assertEquals("Burg. J. van 't Hooft van Damstraat", actual.getStreet());
        assertEquals("1234AB", actual.getZipcode());
        assertEquals("'s Hertogenbosch Ter Apel des Gravenhage", actual.getCity());

        // Test with 'real-world' example of dashes and spaces
        input.setFirstName("jan-willem hendrik");
        input.setLastName("voorst tot voorst-de bruijn-in 't gooij");
        input.setStreet("burg. j. van 't hooft-van damstraat");
        input.setZipcode("1234 ab");
        input.setCity("'s hertogenbosch-ter apel-des gravenhage");

        actual = signUpService.formatFormInput(input);

        assertEquals("Jan-Willem Hendrik", actual.getFirstName());
        assertEquals("Voorst tot Voorst-de Bruijn-in 't Gooij", actual.getLastName());
        assertEquals("Burg. J. van 't Hooft-van Damstraat", actual.getStreet());
        assertEquals("1234AB", actual.getZipcode());
        assertEquals("'s Hertogenbosch-Ter Apel-des Gravenhage", actual.getCity());
    }

    @Test
    void passesElfproefTest() {
        // possibly valid BSN's that pass 'elfproef'
        assertTrue(signUpService.passesElfproef("123456782"));
        assertTrue(signUpService.passesElfproef("215642181"));
        assertTrue(signUpService.passesElfproef("307143818"));
        assertTrue(signUpService.passesElfproef("119340008"));
        assertTrue(signUpService.passesElfproef("613565848"));
        assertTrue(signUpService.passesElfproef("825092991"));
        assertTrue(signUpService.passesElfproef("776726353"));
        assertTrue(signUpService.passesElfproef("446686578"));
        assertTrue(signUpService.passesElfproef("692876121"));
        assertTrue(signUpService.passesElfproef("971041714"));
        assertTrue(signUpService.passesElfproef("264297143"));
        assertTrue(signUpService.passesElfproef("984291301"));
        assertTrue(signUpService.passesElfproef("856018120"));

        // certainly invalid BSN's that fail 'elfproef'
        assertFalse(signUpService.passesElfproef("123456789"));
        assertFalse(signUpService.passesElfproef("863931998"));
        assertFalse(signUpService.passesElfproef("652792547"));
        assertFalse(signUpService.passesElfproef("907234583"));
        assertFalse(signUpService.passesElfproef("308828502"));
        assertFalse(signUpService.passesElfproef("489305575"));
        assertFalse(signUpService.passesElfproef("286111367"));
        assertFalse(signUpService.passesElfproef("420249700"));
        assertFalse(signUpService.passesElfproef("388400871"));
        assertFalse(signUpService.passesElfproef("278675203"));
        assertFalse(signUpService.passesElfproef("125006880"));

        // too short (but divisible by 11)
        assertFalse(signUpService.passesElfproef("85601812"));
        // too long (but divisible by 11)
        assertFalse(signUpService.passesElfproef("8560181200"));
        // all 0's: not a valid BSN but technically divisible by 11
        assertFalse(signUpService.passesElfproef("000000000"));
    }

    @Test
    void isUserNameTakenTest() {
        // 3 usernames that are not taken
        assertFalse(signUpService.isUserNameTaken("lisak"));
        assertFalse(signUpService.isUserNameTaken("machiel"));
        assertFalse(signUpService.isUserNameTaken("minke"));

        // 3 usernames that are taken
        assertTrue(signUpService.isUserNameTaken("mireille"));
        assertTrue(signUpService.isUserNameTaken("moraad"));
        assertTrue(signUpService.isUserNameTaken("sanne"));
    }
}
