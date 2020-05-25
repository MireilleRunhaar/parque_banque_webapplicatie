package nl.team2.parque_banque_server.repository;

import nl.team2.parque_banque_server.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends  CrudRepository<Employee, Integer> {

}