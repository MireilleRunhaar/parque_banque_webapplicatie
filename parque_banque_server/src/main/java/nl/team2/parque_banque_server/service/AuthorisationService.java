package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.repositories.AuthorisationRepository;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorisationService {

    @Autowired
    private AuthorisationRepository authorisationRepository;

    public void saveAuthorisation(Authorisation authorisation) {
        authorisationRepository.save(authorisation);
    }

    public List<Authorisation> findAllByUserName(String username) {
        return authorisationRepository.findAllByUserName(username);
    }
    //get authorisation by Iban (by customer2)
    public Authorisation findAuthorisationByIban(String iban){
        return authorisationRepository.findAuthorisationByIban(iban);


    }


}
