package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("employeeId")
public class EmployeeHomeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/personeel-home")
    public String handleEmployeeHomepage(Model model) {
        if(!model.containsAttribute("employeeId")) {
            return "loginemployee";
        } else {
            long employeeId = (long) model.getAttribute("employeeId");
            Employee employee = employeeService.findEmployeeByLongId(employeeId);
            model.addAttribute("employee", employee);
            return "employeehome";
        }
    }



}
