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

    @GetMapping("/personeel")
    public String handleLogin() {
        return "loginemployee";
    }

    @PostMapping("/personeel")
    public String handleLoginForm(@ModelAttribute("loginemployee") Employee loginemployee, Model model) {
        // Store the entered employee number and password, for easier use in the if else statement
        int loginEmployeeNumber = loginemployee.getEmployeeNumber();
        String loginEmployeePassword = loginemployee.getPassword();

        // Retrieve the employee that has the same employee number as the entered employee number
        Employee employee = employeeService.findByEmployeeNumber(loginEmployeeNumber);

        // Checks for empty fields and no employee with the same employee number as the entered one.
        // TODO validatie loginEmployeeNumber werkt nog niet bij leeg veld
        if (loginEmployeeNumber == 0 || loginEmployeePassword.isEmpty()) {
            model.addAttribute("emptyField", true);
            return "loginemployee";
        } else if (employee == null) {
            model.addAttribute("invalidCredentials", true);
            return "loginemployee";
        } else if (loginEmployeeNumber == employee.getEmployeeNumber() &&
                loginEmployeePassword.equals(employee.getPassword())) {
            return "employeehome";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "loginemployee";
        }
    }

}
