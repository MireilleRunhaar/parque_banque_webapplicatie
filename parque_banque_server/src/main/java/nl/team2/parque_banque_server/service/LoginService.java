package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private EmployeeService employeeService;

    public boolean employeeLoginValidation(LoginEmployeeFormBean loginEmployeeFormBean) {
        Employee employee = employeeService.findByEmployeeNumber(loginEmployeeFormBean.getEmployeeNumber());

        if (employee == null) {
            return false;
        } else return loginEmployeeFormBean.getEmployeeNumber() == employee.getEmployeeNumber() &&
                loginEmployeeFormBean.getPassword().equals(employee.getPassword());
    }
}

