package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserName(String userName);

    @Query(nativeQuery = true, value = "SELECT *, SUM(p.balance_cent) as totale_saldo, COUNT(p.iban) AS aantal_rekeningen FROM user u\n" +
            "JOIN payment_account_account_holders pa ON u.id = pa.account_holders_id \n" +
            "JOIN payment_account p ON p.iban = pa.payment_accounts_iban \n" +
            "WHERE p.dtype = 'PrivateAccount' GROUP BY id, pa.payment_accounts_iban ORDER BY totale_saldo DESC LIMIT 10;")
    List<Customer> getTenRichestPrivateCustomers();

}
