package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BusinessAccountRepository extends CrudRepository <BusinessAccount, String> {

    List<BusinessAccount> findBusinessAccountsByAccountHoldersIn(List<Customer> accountholders);


}
