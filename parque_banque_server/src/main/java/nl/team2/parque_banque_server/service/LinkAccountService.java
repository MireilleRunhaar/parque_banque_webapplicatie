package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkAccountService {

    @Autowired
    AuthorisationService authorisationService;

    public boolean linkAccountValidation(LinkAccountFormBean linkAccountFormBean){
        Authorisation authorisation=authorisationService.findAuthorisationByIban(linkAccountFormBean.getIban());

        //check for same iban. If not present then return false else check for safetycode
        if (authorisation==null){
            return false;
        }
        //controleer of de securitycode die is ingevuld gelijk is aan de securitycode uit de db
        else return linkAccountFormBean.getSecurityCode().equals(authorisation.getSecurityCode());

    }
}
