package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.model.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    //Iterable van alle bedrijven
    public Iterable<Company> companyList(){
        return companyRepo.findAll();
    }



}
