package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findTop10ByCreditAccountOrderByDateAsc(PaymentAccount creditAccount);
    List<Transaction> findTop10ByDebitAccountOrderByDateAsc(PaymentAccount debitAccount);
}
