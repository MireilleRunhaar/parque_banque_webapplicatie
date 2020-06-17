package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.model.repositories.CompanyRepository;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyServiceTest {

    @MockBean
    CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);

    @MockBean
    SectorService sectorService = Mockito.mock(SectorService.class);

    CompanyService companyService = new CompanyService(companyRepository, sectorService);

    @BeforeEach
    public void testSetup(){
        Mockito.when(companyRepository.findCompanyByKvkNr("33029817")).thenReturn
                (new Company("33029817", "NL138048521B91", "Lafayette Radio", new Sector()));

        Mockito.when(sectorService.sectorOpNaam("Landbouw, jacht en visserij")).thenReturn(new Sector());
        Mockito.when(sectorService.sectorOpNaam("Winning van delfstoffen")).
                thenReturn(new Sector("Winning van delfstoffen"));
    }

    @Test
    public void companyTest(){
        CompanyFormBean companyFormBean = new CompanyFormBean();
        companyFormBean.setKvkNr("33029817");
        companyFormBean.setCompanyName("Lafayette Radio");

        boolean actual = companyService.findOneByKVK("33029817").
                getName().equals(companyFormBean.getCompanyName());
                assertTrue(actual);

        boolean actualA = companyService.findOneByKVK("33029817").
                getName().isEmpty();
                assertFalse(actualA);

        boolean actualB = companyService.findOneByKVK("33029817").
                getBtwNr().equals(companyFormBean.getBtwNr());
                assertFalse(actualB);
    }

    @Test
    public void sectorTest(){

        Sector testSector = new Sector();
        testSector.setId(3);
        testSector.setName("Landbouw, jacht en visserij");

        boolean actualC = testSector.getId() < sectorService.
                sectorOpNaam("Winning van delfstoffen").getId();
                assertFalse(actualC);
    }

}
