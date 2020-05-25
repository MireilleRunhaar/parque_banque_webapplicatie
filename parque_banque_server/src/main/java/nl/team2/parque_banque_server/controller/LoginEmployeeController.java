package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/loginemployee")
    public String handleLogin() {
        return "loginemployee";
    }

    @PostMapping("/loginemployee")
    public String handleLoginForm(@ModelAttribute("loginemployee") Employee loginemployee, Model model) {
        // Store the entered email and password, for easier use in the if else statement
        int loginEmployeeNumber = loginemployee.getEmployeeNumber;
        String loginEmployeePassword = loginemployee.getPassword();

        // Retrieve the User that has the same email as the entered email
        Employee employee = employeeService.findByEmployeeNumber(loginEmployeeNumber);

        // If one or more fields are empty, emptyField error
        if (loginEmployeeNumber == 0 || loginEmployeePassword.isEmpty()) {
            model.addAttribute("emptyField", true);
            return "loginemployee";
            // If there's no user with the same email as the entered email, invalidCredentials error
        } else if (employee == null) {
            model.addAttribute("invalidCredentials", true);
            return "loginemployee";
            // If the retrieved email and password are the same as the email and password in the database,
            // you're logged in and get redirected to the homepage
        } else if (loginEmployeeNumber == employee.getId() && loginEmployeePassword.equals(employee.getPassword())) {
            return "employeehome";
            // Else the entered email and/or password aren't valid, invalidCredentials error
        } else {
            model.addAttribute("invalidCredentials", true);
            return "loginemployee";
        }
    }

}
