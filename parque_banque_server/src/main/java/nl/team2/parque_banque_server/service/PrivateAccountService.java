package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateAccountService {
    //-hier komt iban generator, savePrivateAccount fn?

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public PrivateAccountService() {
    }
    //save zelfde als create?
    public void savePrivateAccount(PaymentAccount paymentAccount){
        paymentAccountRepository.save(paymentAccount);
    }

}
