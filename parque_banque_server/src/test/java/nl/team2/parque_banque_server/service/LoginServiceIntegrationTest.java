package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class LoginServiceIntegrationTest {

    private LoginService loginService;

    @Autowired
    public LoginServiceIntegrationTest(LoginService service) {
        super();
        this.loginService = service;
    }

    @Test
    public void TestServiceAvailable() {
        assertNotNull(loginService);
    }

    @Test
    public void TestEmployeeRepoAvailable() {
        assertNotNull(loginService.getEmployeeService());
    }

    @Test
    public void TestCustomerRepoAvailable() {
        assertNotNull(loginService.getCustomerService());
    }
}
