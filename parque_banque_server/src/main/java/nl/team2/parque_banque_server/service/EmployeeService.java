package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
/*
    public Employee findById(int idLong) {
        int id = Math.toIntExact(idLong);
        Optional<Employee> employeeOption = employeeRepo.findById(id);
        if (employeeOption.isPresent()) {
            return employeeOption.get();
        }
        return null;
    }*/

    public Employee findEmployeeByLongId(long idLong) {
        return employeeRepo.findEmployeeById(idLong);
    }

    public Employee findByEmployeeNumber(int employeeNumber) {
        return employeeRepo.findEmployeeByEmployeeNumber(employeeNumber);
    }

}
