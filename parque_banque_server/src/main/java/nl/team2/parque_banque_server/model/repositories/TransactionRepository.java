package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
