package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LinkAccountServiceIntegrationTest {

    private LinkAccountService linkAccountService;

    @Autowired
    public LinkAccountServiceIntegrationTest(LinkAccountService linkAccountService){
        super();
        this.linkAccountService=linkAccountService;
    }
    @Test
    public void getAuthorisationRepo(){
        assertNotNull(linkAccountService.getAuthorisationService());
    }

}