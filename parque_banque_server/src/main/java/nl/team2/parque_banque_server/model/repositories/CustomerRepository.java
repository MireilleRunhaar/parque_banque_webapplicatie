package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserName(String userName);


    @Query(value="select  u.first_name, u.affix, u.sur_name, sum(p.balance_cent) as totale_saldo\n" +
            "from user u \n" +
            "join payment_account_account_holders pa on u.id=pa.account_holders_id\n" +
            "join payment_account p on p.iban=pa.payment_accounts_iban\n" +
            "where p.dtype='BusinessAccount' && u.dtype='Customer'\n" +
            "group by id\n" +
            "order by totale_saldo DESC\n" +
            "limit 10", nativeQuery = true)
    List<Customer> top10Customers();
}
