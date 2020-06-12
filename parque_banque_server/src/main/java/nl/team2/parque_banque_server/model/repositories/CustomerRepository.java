package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserName(String userName);

    @Query("SELECT c FROM Customer c, PaymentAccount p " +
            "WHERE p.iban='NL10PARQ0100004002'")
    List<Customer> returnBsnCustomers();

    @Query(nativeQuery = true, value = "SELECT *, SUM(p.balance_cent) as totale_saldo, COUNT(p.iban) AS aantal_rekeningen FROM user u\n" +
            "JOIN payment_account_account_holders pa ON u.id = pa.account_holders_id \n" +
            "JOIN payment_account p ON p.iban = pa.payment_accounts_iban \n" +
            "where p.dtype = 'PrivateAccount' group by id order by totale_saldo DESC limit 10;")
    List<Customer> returnCustomers();

    @Query(nativeQuery = true, value = "select *, sum(p.balance_cent) as totale_saldo from user u \n" +
            "join payment_account_account_holders pa on u.id = pa.account_holders_id \n" +
            "join payment_account p on p.iban = pa.payment_accounts_iban \n" +
            "where p.dtype = 'BusinessAccount' group by id order by totale_saldo DESC limit 10;")
    List<Customer> returnBusinessCustomers();
}
