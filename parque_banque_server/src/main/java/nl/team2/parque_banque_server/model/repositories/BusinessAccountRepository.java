package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.BusinessAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessAccountRepository extends CrudRepository <BusinessAccount, Id> {

}
