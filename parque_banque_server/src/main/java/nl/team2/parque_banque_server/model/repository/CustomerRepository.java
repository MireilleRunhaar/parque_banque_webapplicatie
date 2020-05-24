package nl.team2.parque_banque_server.model.repository;

import nl.team2.parque_banque_server.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Integer>{
}
