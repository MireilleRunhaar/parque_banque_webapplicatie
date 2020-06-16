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
            "WHERE p.dtype = 'PrivateAccount' GROUP BY id ORDER BY totale_saldo DESC LIMIT 10;")
    List<Customer> getTenRichestPrivateCustomers();



    @Query(value="select  *, sum(p.balance_cent) as totale_saldo, count(p.iban) as aantal_rekeningen\n" +
            "from user u \n" +
            "join payment_account_account_holders pa on u.id=pa.account_holders_id\n" +
            "join payment_account p on p.iban=pa.payment_accounts_iban\n" +
            "where p.dtype='BusinessAccount' \n" +
            "group by id\n" +
            "order by totale_saldo DESC\n" +
            "limit 10", nativeQuery = true)
    List<Customer> getTenRichestBusinessCustomers();

    @Query(value = "SELECT *, count(t.id) aantal_transacties \n" +
            "FROM USER u JOIN payment_account_account_holders pah \n" +
            "ON u.id = pah.account_holders_id JOIN payment_account pa \n" +
            "ON pah.payment_accounts_iban = pa.iban JOIN transaction t \n" +
            "ON pa.iban = t.credit_account_iban \n" +
            "OR pa.iban = t.debit_account_iban \n" +
            "WHERE pa.dtype = 'BusinessAccount' \n" +
            "GROUP BY u.id \n" +
            "ORDER BY aantal_transacties DESC LIMIT 10", nativeQuery = true)
    List<Customer> getTenMostActiveCustomers();
}
