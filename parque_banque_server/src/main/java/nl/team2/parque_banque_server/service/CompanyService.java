package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.repositories.CompanyRepository;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;
    @Autowired
    private SectorService sectorService;

    //Iterable van alle bedrijven
    public Iterable<Company> companyList(){
        return companyRepo.findAll();
    }

    public Company findOneByKVK(String kvk){
        return companyRepo.findCompanyByKvkNr(kvk);
    }

    public void saveCompany(Company company){
        companyRepo.save(company);
    }

    //Methode om een formBean om te zetten in een Company
    public Company createCompanyOutOfBean(CompanyFormBean companyFormBean){
        Company newCompany = new Company();
        newCompany.setKvkNr(companyFormBean.getKvkNr());
        newCompany.setBtwNr(companyFormBean.getBtwNr());
        newCompany.setName(companyFormBean.getName());
        newCompany.setSector(sectorService.sectorOpNaam(companyFormBean.getSectorName()));
        return newCompany;
    }

}
