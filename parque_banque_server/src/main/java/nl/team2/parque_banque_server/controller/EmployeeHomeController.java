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
public class EmployeeHomeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/personeel-home")
    public String handleEmployeeHomepage(Model model) {
        if(model.getAttribute("employeeId") == null) {
            return "loginemployee";
        } else {
            //Create an employee from the session attribute
            Employee employee = employeeService.findEmployeeBySAId(model.getAttribute("employeeId"));
            model.addAttribute("employee", employee);
            return "employeehome";
        }
    }
}
