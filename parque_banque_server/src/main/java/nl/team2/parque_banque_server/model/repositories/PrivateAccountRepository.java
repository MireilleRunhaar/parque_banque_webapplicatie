package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateAccountRepository extends CrudRepository<PrivateAccount,String> {

    List<PrivateAccount>  findPrivateAccountsByAccountHoldersIn(List<Customer> accountholders);

}
