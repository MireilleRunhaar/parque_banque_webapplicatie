package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpServiceTest {



    @Test
    void CapitalizeStringsTest() {
        SignUpFormBean input = new SignUpFormBean();
        input.setFirstName("jan-willem hendrik");
        input.setLastName("voorst tot voorst-de bruijn-in 't gooij");
        input.setStreet("burg. j. van 't hooft-van damstraat");
        input.setZipcode("1234 ab");
        input.setCity("'s hertogenbosch-ter apel");

        SignUpFormBean actual = SignUpService.formatFormInput(input);

        assertEquals("Jan-Willem Hendrik", actual.getFirstName());
        assertEquals("Voorst tot Voorst-de Bruijn-in 't Gooij", actual.getLastName());
        assertEquals("Burg. J. van 't Hooft-van Damstraat", actual.getStreet());
        assertEquals("1234AB", actual.getZipcode());
        assertEquals("'s Hertogenbosch-Ter Apel", actual.getCity());

    }

    @Test
    void passesElfproefTest() {
        // possibly valid BSN's that pass 'elfproef'
        assertTrue(SignUpService.passesElfproef("123456782"));
        assertTrue(SignUpService.passesElfproef("215642181"));
        assertTrue(SignUpService.passesElfproef("307143818"));
        assertTrue(SignUpService.passesElfproef("119340008"));
        assertTrue(SignUpService.passesElfproef("613565848"));
        assertTrue(SignUpService.passesElfproef("825092991"));
        assertTrue(SignUpService.passesElfproef("776726353"));
        assertTrue(SignUpService.passesElfproef("446686578"));
        assertTrue(SignUpService.passesElfproef("692876121"));
        assertTrue(SignUpService.passesElfproef("971041714"));
        assertTrue(SignUpService.passesElfproef("264297143"));
        assertTrue(SignUpService.passesElfproef("984291301"));
        assertTrue(SignUpService.passesElfproef("856018120"));

        // certainly invalid BSN's that fail 'elfproef'
        assertFalse(SignUpService.passesElfproef("123456789"));
        assertFalse(SignUpService.passesElfproef("863931998"));
        assertFalse(SignUpService.passesElfproef("652792547"));
        assertFalse(SignUpService.passesElfproef("907234583"));
        assertFalse(SignUpService.passesElfproef("308828502"));
        assertFalse(SignUpService.passesElfproef("489305575"));
        assertFalse(SignUpService.passesElfproef("286111367"));
        assertFalse(SignUpService.passesElfproef("420249700"));
        assertFalse(SignUpService.passesElfproef("388400871"));
        assertFalse(SignUpService.passesElfproef("278675203"));
        assertFalse(SignUpService.passesElfproef("125006880"));

        // too short (but divisible by 11)
        assertFalse(SignUpService.passesElfproef("85601812"));
        // too long (but divisible by 11)
        assertFalse(SignUpService.passesElfproef("8560181200"));

    }
}
