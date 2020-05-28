package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee findByEmployeeNumber(int employeeNumber) {
        return employeeRepo.findEmployeeByEmployeeNumber(employeeNumber);
    }

    public Employee findEmployeeByEmployeeId(long id) {
        return employeeRepo.findEmployeeById(id);
    }

    //Find Employee By Session Attribute Id
    public Employee findEmployeeBySAId(Object object) {
        long id = (long) object;
        return findEmployeeByEmployeeId(id);
    }
}
