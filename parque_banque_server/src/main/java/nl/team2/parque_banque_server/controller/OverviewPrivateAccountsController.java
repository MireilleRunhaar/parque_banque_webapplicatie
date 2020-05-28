package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("employeeId")
public class OverviewPrivateAccountsController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/overzicht-particulier")
    public String handleOverviewPrivate(Model model) {
        //Create an employee from the session attribute
        Employee employee = employeeService.findEmployeeBySAId(model.getAttribute("employeeId"));

        if(!model.containsAttribute("employeeId")) {
            return "loginemployee";
        } else if (employee.getRole().getId() != 1)   {
            return "redirect:/personeel-home";
        } else {
            return "overviewprivateaccounts";
        }
    }
}
