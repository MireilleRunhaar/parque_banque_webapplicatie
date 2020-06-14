package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CompanyServiceIntegrationTest {

    private CompanyService companyService;

    @Autowired
    public CompanyServiceIntegrationTest(CompanyService companyService) {
        super();
        this.companyService = companyService;
    }

    @Test
    public void testCompanyServiceAvailable(){
        assertNotNull(companyService);
    }

    @Test
    public void testSectorServiceAvailable(){
        assertNotNull(companyService.getSectorService());
    }

    @Test
    public void testCompanyRepoAvailable(){
        assertNotNull(companyService.getCompanyRepo());
    }

}
