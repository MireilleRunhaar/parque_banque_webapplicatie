package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Long> {

    List<Transaction> findTop10ByCreditAccount_IbanOrDebitAccount_IbanOrderByDateDesc(String creditAccount_iban,
                                                                                      String debitAccount_iban);
    List<Transaction> findAll();

}
