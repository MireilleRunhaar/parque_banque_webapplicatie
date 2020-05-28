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
public class OverviewBusinessAccountsController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/overzicht-bedrijven")
    public String handleOverviewBusiness(Model model) {
        long employeeId = (long) model.getAttribute("employeeId");
        Employee employee = employeeService.findEmployeeByLongId(employeeId);

        if(!model.containsAttribute("employeeId")) {
            return "loginemployee";
        } else if (employee.getRole().getId() != 2)   {
            return "redirect:/personeel-home";
        } else {
            return "overviewprivateaccounts";
        }
    }
}
