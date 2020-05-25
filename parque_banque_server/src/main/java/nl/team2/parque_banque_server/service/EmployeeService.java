package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private Employee employeeRepo;

    public Employee findById(int id) {
        Optional<Employee> employeeOption = employeeRepo.findById(id);
        if (employeeOption.isPresent()) {
            return employeeOption.get();
        }
        return null;
    }

}
