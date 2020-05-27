package nl.team2.parque_banque_server.repository;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByUserName(String userName);

    // klant object uit de sessie halen
    Customer findCustomerByCustomerId(long id);


}
