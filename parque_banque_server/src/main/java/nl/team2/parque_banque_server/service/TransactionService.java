package nl.team2.parque_banque_server.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    // TODO: 05/06/2020 valideer bestaan IBAN en voldoende saldo
    public boolean validateIBANExcists() {
        return false;
    }

    public boolean validateSufficientFunds() {
        return false;
    }
}
