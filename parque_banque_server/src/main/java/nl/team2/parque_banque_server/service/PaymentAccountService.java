package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepo;

    public PaymentAccountService(){
        super();
    }
}
