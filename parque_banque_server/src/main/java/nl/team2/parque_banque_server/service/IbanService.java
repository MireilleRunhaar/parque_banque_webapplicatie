package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IbanService {

    public final int INCREMENT=1;
    public final int LOWER_LIMIT=0;
    public final int UPPER_LIMIT=8;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public IbanService() {
    }

    // get last the last added iban and add 1.
    public String createNewIban(){
        PaymentAccount lastAddedPaymentAccouny=paymentAccountRepository.findTopByOrderByIbanDesc();
        String lastAddedIban=lastAddedPaymentAccouny.getIban(); //bijv NL02PNBQ1234123400

        // laatste 10 cijferige nummerreeks splitsen en hierbij 1 optellen ; hier hangt (nog) geen limiet aan
        String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL02PNBQ"
                +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT,lastAddedIban.length()))+INCREMENT);
        return newIban;

    }

}
