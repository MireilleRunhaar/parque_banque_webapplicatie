package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.repositories.AuthorisationRepository;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkAccountService {

    @Autowired
    AuthorisationService authorisationService;

    public boolean linkAccountValidation(LinkAccountFormBean linkAccountFormBean, String username) {
        List<Authorisation> authorisationList = authorisationService.findAllByUserName(username);
        if (authorisationList.size() != 0) {
            for (Authorisation authorisation : authorisationList) {
                if (authorisation.getSecurityCode().equals(linkAccountFormBean.getSecurityCode())
                        && authorisation.getIban().equals(linkAccountFormBean.getIban())) {
                    return true;
                }
            }
        }
        return false;
    }


    public AuthorisationService getAuthorisationService() {
        return authorisationService;
    }

}
