package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentAccountService {
    //-hier komt iban generator, savePrivateAccount fn?

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public PaymentAccountService() {
    }

    public void savePrivateAccount(PaymentAccount paymentAccount){
        paymentAccountRepository.save(paymentAccount);
    }



    @Service
    public static class IbanService {

        public final int INCREMENT=1;
        public final int LOWER_LIMIT=0;
        public final int UPPER_LIMIT=8;
        public final String IBAN_00="NL01PARQ1234123400";

        @Autowired
        private PaymentAccountRepository paymentAccountRepository;

        public IbanService() {
        }

        // get last the last added iban and add 1.
        public String createNewIban(){
           PaymentAccount lastAddedPaymentAccount=paymentAccountRepository.findTopByOrderByIbanDesc();
           if (lastAddedPaymentAccount==null){
               String lastAddedIban=IBAN_00;
               String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ"
                       +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT,lastAddedIban.length()))+INCREMENT);
               return newIban;
           } else {
               String lastAddedIban=lastAddedPaymentAccount.getIban();
               // laatste 10 cijferige nummerreeks splitsen en hierbij 1 optellen ; hier hangt (nog) geen limiet aan
               String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL02PNBQ"
                       +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT,lastAddedIban.length()))+INCREMENT);
               return newIban;
           }

        }


    }
}
