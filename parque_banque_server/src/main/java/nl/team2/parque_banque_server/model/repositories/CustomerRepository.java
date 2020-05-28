package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserName(String userName);

    Customer findCustomerById(long id);

}
