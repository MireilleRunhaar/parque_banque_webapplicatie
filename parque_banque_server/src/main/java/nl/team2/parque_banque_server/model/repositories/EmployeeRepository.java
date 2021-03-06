package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends  CrudRepository<Employee, Long> {

    Employee findEmployeeByEmployeeNumber(int employeeNumber);

    Employee findEmployeeById(long id);

    Employee findTopByRole_Name(String roleName);
}