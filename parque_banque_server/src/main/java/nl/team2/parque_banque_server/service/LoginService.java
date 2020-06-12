package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    public boolean employeeLoginValidation(LoginEmployeeFormBean loginEmployeeFormBean) {
        Employee employee = employeeService.findByEmployeeNumber(loginEmployeeFormBean.getEmployeeNumber());

        //Check for same EmployeeNumber is done by if, check for the same password is done by else
        if (employee == null) {
            return false;
        } else return loginEmployeeFormBean.getPassword().equals(employee.getPassword());
    }

    public boolean customerLoginValidation(LoginCustomerFormBean loginCustomerFormBean) {
        return loginValidation(loginCustomerFormBean.getUserName(), loginCustomerFormBean.getPassword());
    }

    public boolean loginValidation(String username, String password) {
        Customer customer = customerService.findByUserName(username);

        // Check for same UserName is done by if, check for the same password is done by else
        if (customer == null) {
            return false;
        } else return password.equals(customer.getPassword());
    }
}
