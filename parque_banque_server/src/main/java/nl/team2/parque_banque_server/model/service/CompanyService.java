package nl.team2.parque_banque_server.model.service;

import nl.team2.parque_banque_server.model.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;
}
