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

}
