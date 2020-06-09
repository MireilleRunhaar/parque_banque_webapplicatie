package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LinkAccountService {

    @Autowired
    AuthorisationService authorisationService;

    public boolean linkAccountValidation(LinkAccountFormBean linkAccountFormBean, AddAccountHolderFormBean addAccountHolderFormBean) {
        // haal een lijst op uit de database op basis van de username uit de accountholderformbean
        //als er geen authorisaties zijn dan is het resultaat false.
        List<Authorisation> authorisationList = authorisationService.findAllByUserName(addAccountHolderFormBean.getUsername());
        if (authorisationList.size() == 0) {
            return false;
        }
        // Voor elke iban op de lijst check of de iban uit de db overeenkomt met de iban uit de bean. 
        // en check of de securitycode overeenkomt met de securitycode uit de bean.
        // zo ja, return true, zo nee return false
        else {
            for (Authorisation authorisation : authorisationList) {
                return authorisation.getSecurityCode().equals(linkAccountFormBean.getSecurityCode())
                        && authorisation.getIban().equals(linkAccountFormBean.getIban());
            }
            return false;
        }
    }

}
