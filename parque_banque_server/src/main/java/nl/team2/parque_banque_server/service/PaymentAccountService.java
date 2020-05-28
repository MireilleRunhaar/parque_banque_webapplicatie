package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;

import nl.team2.parque_banque_server.model.repositories.PrivateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentAccountService {
    //-hier komt iban generator, savePrivateAccount fn?



    public PaymentAccountService() {
    }


    @Service
    public static class IbanService {

        public final int INCREMENT=1;
        public final int LOWER_LIMIT=0;
        public final int UPPER_LIMIT=9;
        public final String IBAN_00="NL01PARQ0100000000";

        @Autowired
        private PaymentAccountRepository paymentAccountRepository;
        @Autowired
        private PrivateAccountRepository privateAccountRepository;

        public IbanService() {
        }

        // get last the last added iban and add 1.
        public String createNewIban(){
           String lastAddedPaymentAccount=privateAccountRepository.findTopByOrderByIbanDesc().getIban();
           if (lastAddedPaymentAccount==null){
               String lastAddedIban=IBAN_00;
               String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ0"
                       +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT))+INCREMENT);
               return newIban;
           } else {

               // laatste 9 cijferige nummerreeks splitsen en hierbij 1 optellen ; hier hangt (nog) geen limiet aan
               String newIban=lastAddedPaymentAccount.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ0"
                       +(Integer.parseInt(lastAddedPaymentAccount.substring(UPPER_LIMIT))+INCREMENT);
               return newIban;
           }

        }


    }
}
