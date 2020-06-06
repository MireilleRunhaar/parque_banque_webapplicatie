package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SignUpServiceIntegrationTest {

    private SignUpService signUpService;

    @Autowired
    public SignUpServiceIntegrationTest(SignUpService signUpService) {
        super();
        this.signUpService = signUpService;
    }

    @Test
    void testSignUpServiceAvailable() {
        assertNotNull(signUpService);
    }

    @Test
    void testCustomerServiceAvailabe() {
        assertNotNull(signUpService.getCustomerService());
    }

    @Test
    void testCustomerRepoAvailable() {
        assertNotNull(signUpService.getCustomerService().getCustomerRepo());
    }

    @Test
    void testCustomerService() {
        // usernames that exist in database (users with id 1-6):
        assertTrue(signUpService.isUserNameTaken("dmcelree0"));
        assertTrue(signUpService.isUserNameTaken("scollingwood1"));
        assertTrue(signUpService.isUserNameTaken("sterne2"));
        assertTrue(signUpService.isUserNameTaken("dscogin3"));
        assertTrue(signUpService.isUserNameTaken("yshadbolt4"));
        assertTrue(signUpService.isUserNameTaken("ngrimshaw5"));

        // usernames that do not exist in database:
        assertFalse(signUpService.isUserNameTaken("abcde"));
        assertFalse(signUpService.isUserNameTaken("qwerty"));
        assertFalse(signUpService.isUserNameTaken("markruttesaccount"));
        assertFalse(signUpService.isUserNameTaken("ikbencoronabeu"));
        assertFalse(signUpService.isUserNameTaken("hvamiwc19"));
    }
}
