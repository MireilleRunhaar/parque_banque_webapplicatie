package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LinkAccountService {

    @Autowired
    AuthorisationService authorisationService;

    public boolean linkAccountValidation(LinkAccountFormBean linkAccountFormBean) {
        //maak een lijst met autorisaties. Haal deze op op basis van de username en het ibannummer
        List<Authorisation> authorisationList = authorisationService.findAllByUserName(linkAccountFormBean.getIban());
        if (authorisationList.size() == 0) {
            return false;
        }
        //check for every iban of de security code uit de db overeenkomt met de securitycode in de bean.
        //check for evetsame iban. If not present then return false else check for safetycode
        // Voor elke iban op de lijst check of de securitycode uit de db overeenkomt met de ingevulde.
        else {
            for (Authorisation authorisation : authorisationList) {
                return authorisation.getSecurityCode().equals(linkAccountFormBean.getSecurityCode());

            }
            return true;
        }
    }
}
