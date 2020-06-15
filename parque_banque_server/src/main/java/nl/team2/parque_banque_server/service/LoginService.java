package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private EmployeeService employeeService;

    private CustomerService customerService;

    @Autowired
    public LoginService(EmployeeService employeeService, CustomerService customerService) {
        super();
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    public boolean employeeLoginValidation(LoginEmployeeFormBean loginEmployeeFormBean) {
        Employee employee = employeeService.findByEmployeeNumber(loginEmployeeFormBean.getEmployeeNumber());

        // Check for same EmployeeNumber is done by if, check for the same password is done by else
        if (employee == null) {
            return false;
        } else return loginEmployeeFormBean.getPassword().equals(employee.getPassword());
    }

    public boolean customerLoginValidation(LoginCustomerFormBean loginCustomerFormBean) {
        Customer customer = customerService.findByUserName(loginCustomerFormBean.getUserName());

        // Check for same UserName is done by if, check for the same password is done by else
        if (customer == null) {
            return false;
        } else return loginCustomerFormBean.getPassword().equals(customer.getPassword());
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}

