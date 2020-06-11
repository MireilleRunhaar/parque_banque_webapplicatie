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
}
