package nl.team2.parque_banque_server.repository;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentAccountRepository extends CrudRepository<PaymentAccount, Integer> {

//   List<PaymentAccount> findPaymentAccountsByAccountHolders(Customer customer);


}
