package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Sector;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BusinessAccountRepository extends CrudRepository <BusinessAccount, String> {

    List<BusinessAccount> findBusinessAccountsByAccountHoldersIn(List<Customer> accountholders);

    BusinessAccount findTopByOrderByIbanDesc();

    @Query(value = "select *,  avg(p.balance_cent) as gemiddelde_saldo\n" +
            "from sector s \n" +
            "join company c on s.id=c.sector_id\n" +
            "join payment_account p on c.kvk_nr=p.company_kvk_nr\n" +
            "where p.dtype='BusinessAccount'\n" +
            "group by s.name",nativeQuery = true)
    List<BusinessAccount>getAverageBalanceSector();



}
