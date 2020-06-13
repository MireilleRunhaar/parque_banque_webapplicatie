package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.PaymentAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentAccountRepository extends CrudRepository<PaymentAccount, String> {

    public List<PaymentAccount> findAllByAccountHoldersAndIban(String iban, String customerId);

}