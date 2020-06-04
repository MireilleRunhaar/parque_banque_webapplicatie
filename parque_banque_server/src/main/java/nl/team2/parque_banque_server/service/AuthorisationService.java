package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.repositories.AuthorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorisationService {

    @Autowired
    private AuthorisationRepository authorisationRepository;

    List<Authorisation> findByUserName(String username) {
        return authorisationRepository.findByUserName(username);
    }
}
