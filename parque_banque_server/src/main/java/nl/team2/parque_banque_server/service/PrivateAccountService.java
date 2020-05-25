package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repository.PrivateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateAccountService {
    //-hier komt iban generator, savePrivateAccount fn?

    @Autowired
    private PrivateAccountRepository privateAccountRepository;

    public PrivateAccountService() {
    }
    //save zelfde als create?
    public void savePrivateAccount(PaymentAccount paymentAccount ){
        privateAccountRepository.save(paymentAccount);
    }
}
