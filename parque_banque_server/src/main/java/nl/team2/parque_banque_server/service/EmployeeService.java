package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee findById(long id) {
        Optional<Employee> employeeOption = employeeRepo.findById(id);
        return employeeOption.orElse(null);
    }

    public Employee findByEmployeeNumber(int employeeNumber) {
        return employeeRepo.findEmployeeByEmployeeNumber(employeeNumber);
    }
    
    //Find Employee By Session Attribute Id
    public Employee findEmployeeBySAId(Object object) {
        long id = (long) object;
        return findById(id);
    }

    public Employee findOneByRoleName(String roleName){
        return employeeRepo.findTopByRole_Name(roleName);
    }
}
