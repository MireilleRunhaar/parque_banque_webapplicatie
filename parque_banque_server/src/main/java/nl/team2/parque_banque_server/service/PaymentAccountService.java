package nl.team2.parque_banque_server.service;


import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.model.repositories.PrivateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.util.Optional;


@Service
public class PaymentAccountService {

    public static final double CENTS_IN_EURO = 100.00;

    @Autowired
    private PaymentAccountRepository paymentAccountRepo;

    public PaymentAccountService() {
    }

    /**
     * takes a balance in cents and converts it to euro's in a customerfriendly way
     * @param balanceCents
     * @return String of euro sign and amount in euro's
     */
    public String balanceInEuros(long balanceCents){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(balanceCents / CENTS_IN_EURO);
    }

    public boolean validateFunds(String iban, long transactionAmount){
        PaymentAccount paymentAccount = findOneByIban(iban);
        return paymentAccount.validateSufficientFunds(transactionAmount);
    }

    public PaymentAccount findOneByIban(String iban){
       Optional<PaymentAccount> optional= paymentAccountRepo.findById(iban);
       return optional.orElse(null);
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
        @Autowired
        private BusinessAccountRepository businessAccountRepository;

        public IbanService() {
        }

//        // get last the last added iban and add 1.
//        public String createNewIban(){
//           String lastAddedPaymentAccount=privateAccountRepository.findTopByOrderByIbanDesc().getIban();
//           if (lastAddedPaymentAccount==null){
//               String lastAddedIban=IBAN_00;
//               String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ0"
//                       +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT))+INCREMENT);
//               return newIban;
//           } else {
//
//               // laatste 9 cijferige nummerreeks splitsen en hierbij 1 optellen ; hier hangt (nog) geen limiet aan
//               String newIban=lastAddedPaymentAccount.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ0"
//                       +(Integer.parseInt(lastAddedPaymentAccount.substring(UPPER_LIMIT))+INCREMENT);
//               return newIban;
//           }
//
//        }

        public String createNewIban(){
            String lastAddedPrivateIban=privateAccountRepository.findTopByOrderByIbanDesc().getIban();
            String lastAddedBusinessIban=businessAccountRepository.findTopByOrderByIbanDesc().getIban();
            int privateIban=Integer.parseInt(lastAddedPrivateIban.substring(UPPER_LIMIT));
            int businessIban=Integer.parseInt(lastAddedBusinessIban.substring(UPPER_LIMIT));
            if(lastAddedPrivateIban==null||lastAddedBusinessIban==null){
                String lastAddedIban=IBAN_00;
                String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT) //"NL01PARQ0"
                        +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT))+INCREMENT);
                return newIban;
            } else if (privateIban>businessIban){
                String lastAddedIban=lastAddedPrivateIban;
                String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT)
                        +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT))+INCREMENT);
                return newIban;
            } else {
                String lastAddedIban=lastAddedBusinessIban;
                String newIban=lastAddedIban.substring(LOWER_LIMIT,UPPER_LIMIT)
                        +(Integer.parseInt(lastAddedIban.substring(UPPER_LIMIT))+INCREMENT);
                return newIban;
            }


        }


    }
}
