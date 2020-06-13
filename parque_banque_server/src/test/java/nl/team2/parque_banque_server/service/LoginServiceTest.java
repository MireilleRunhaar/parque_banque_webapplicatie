package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.model.Role;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private final static String PASSWORD = "MockPassword";
    private final static String WRONG_PASSWORD = "ABCDEFG";

    @MockBean
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);

    @MockBean
    CustomerService customerService = Mockito.mock(CustomerService.class);

    LoginService loginService = new LoginService(employeeService, customerService);

    @BeforeEach
    public void testSetup() {
        Mockito.when(employeeService.findByEmployeeNumber(1)).thenReturn(new Employee(1,
                PASSWORD, new Role ()));
        Mockito.when(employeeService.findByEmployeeNumber(5)).thenReturn(null);
        Mockito.when(customerService.findByUserName("Jaap")).thenReturn(new Customer("123456789", "Jaap", PASSWORD));
        Mockito.when(customerService.findByUserName("Jolien")).thenReturn(null);
    }

    @Test
    public void employeeValidationTest() {
        LoginEmployeeFormBean employeeBean = new LoginEmployeeFormBean();
        employeeBean.setEmployeeNumber(1);
        employeeBean.setPassword(PASSWORD);

        boolean actual = loginService.employeeLoginValidation(employeeBean);
        assertTrue(actual);

        employeeBean.setPassword(WRONG_PASSWORD);
        boolean actual2 = loginService.employeeLoginValidation(employeeBean);
        assertFalse(actual2);

        employeeBean.setEmployeeNumber(5);
        boolean actual3 = loginService.employeeLoginValidation(employeeBean);
        assertFalse(actual3);

        employeeBean.setEmployeeNumber(0);
        boolean actual4 = loginService.employeeLoginValidation(employeeBean);
        assertFalse(actual4);
    }

    @Test
    public void customerValidationTest() {
        LoginCustomerFormBean customerBean = new LoginCustomerFormBean();
        customerBean.setUserName("Jaap");
        customerBean.setPassword(PASSWORD);

        boolean actual = loginService.customerLoginValidation(customerBean);
        assertTrue(actual);

        customerBean.setPassword(WRONG_PASSWORD);
        boolean actual2 = loginService.customerLoginValidation(customerBean);
        assertFalse(actual2);

        customerBean.setUserName("Jolien");
        boolean actual3 = loginService.customerLoginValidation(customerBean);
        assertFalse(actual3);

        customerBean.setUserName(null);
        boolean actual4 = loginService.customerLoginValidation(customerBean);
        assertFalse(actual4);
    }
}


