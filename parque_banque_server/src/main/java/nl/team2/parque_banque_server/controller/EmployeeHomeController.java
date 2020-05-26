package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("employee")
public class EmployeeHomeController {

    @GetMapping("/personeel-home")
    public ModelAndView handleLogin() {
        return new ModelAndView("employeehome");
    }
}
