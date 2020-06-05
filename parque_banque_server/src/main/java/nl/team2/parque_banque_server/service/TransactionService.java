package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepo;

    public boolean validateIBANExcists(String iban) {
        return paymentAccountRepo.findById(iban).isPresent();
    }

    public boolean validateSufficientFunds() {
        return false;
    }
}
