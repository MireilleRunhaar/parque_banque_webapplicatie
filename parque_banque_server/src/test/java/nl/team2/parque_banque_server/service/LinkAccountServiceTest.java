package nl.team2.parque_banque_server.service;

import com.sun.xml.bind.v2.TODO;
import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.repositories.AuthorisationRepository;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class LinkAccountServiceTest {

    private final static String SECURITYCODE="MockSecuritycode";

    @MockBean
    AuthorisationRepository authorisationRepo = Mockito.mock(AuthorisationRepository.class);

    @MockBean
    AuthorisationService authorisationService = Mockito.mock(AuthorisationService.class);

    LinkAccountService linkAccountService = new LinkAccountService();

    public LinkAccountServiceTest(){super();}

    @BeforeEach
    public void testSetup(){
        Mockito.when(authorisationRepo.findByIban("NL27INGB0006646847")).thenReturn(new Authorisation("minkeuser",SECURITYCODE, "NL27INGB0006646847"));
        Mockito.when(authorisationRepo.findByIban("NL10PARQ0100002304")).thenReturn(null);

    }
//    TODO
//    Unit test werkend krijgen

//    @Test
//    void linkAccountValidation() {
//        LinkAccountFormBean linkAccountFormBean = new LinkAccountFormBean();
//        linkAccountFormBean.setIban("NL27INGB0006646847");
//        linkAccountFormBean.setSecurityCode(SECURITYCODE);
//
//        boolean actual = linkAccountService.linkAccountValidation(linkAccountFormBean,String username);
//
//    }

    @Test
    void getAuthorisationService() {
    }
}