package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.PrivateAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrivateAccountRepository extends CrudRepository<PrivateAccount,String> {


    PrivateAccount findTopByOrderByIbanDesc();
    //PrivateAccount findIdByIban(String iban);
}
