package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeHomeController {

    @GetMapping("/personeel-home")
    public String handleLogin() {
        return "employeehome";
    }
}
