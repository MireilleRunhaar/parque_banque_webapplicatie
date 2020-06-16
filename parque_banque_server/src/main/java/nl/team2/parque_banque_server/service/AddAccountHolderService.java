package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class AddAccountHolderService {

    @Autowired
    private AuthorisationService authorisationService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentAccountService paymentAccountService;

    public Authorisation createAuthorisation(String username, String code, String iban) {
        return new Authorisation(username, code, iban);
    }

    public boolean isInsecureCode(String code) {
        return isSameDigits(code) || isRowOfIncrements(code);
    }

    private boolean isSameDigits(String code) {
        for (int index = 0; index < code.length() - 1; index++) {
            if (Integer.parseInt(code.substring(index, index + 1)) != Integer.parseInt(code.substring(index + 1, index + 2))) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowOfIncrements(String code) {
        // Get the first two digits and determine the difference
        int num1 = Integer.parseInt(code.substring(0, 1));
        int numCheck = Integer.parseInt(code.substring(1, 2));
        int diff = numCheck - num1;

        for (int index = 2; index < code.length(); index++) {
            int nextnum = Integer.parseInt(code.substring(index, index + 1));
            if (diff != nextnum - numCheck) {
                return false;
            }
            numCheck = nextnum;
        }
        return true;
    }


    public boolean customerAlreadyAccountHolder(String username, String iban) {
        Customer customer = customerService.findByUserName(username);
        PaymentAccount paymentAccount = paymentAccountService.findOneByIban(iban);

        return paymentAccount.getAccountHolders().contains(customer);
    }

    public Authorisation authorisationInDatabase(String username, String code, String iban) {
        List<Authorisation> authorisationList = authorisationService.findAllByUserName(username);
        for (Authorisation a : authorisationList) {
            if (a.getIban().equalsIgnoreCase(iban) && a.getSecurityCode().equalsIgnoreCase(code)) {
                return a;
            }
        }
        return null;
    }

    public boolean validateInput(String username, String code, String iban) {
        return customerService.findByUserName(username) != null && !isInsecureCode(code) &&
                paymentAccountService.findOneByIban(iban) != null;
    }
}
