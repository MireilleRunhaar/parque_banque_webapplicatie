package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/contact")
    public String goToContactPage(){
        return "contact";
    }

    @GetMapping("/inloggen")
    public String goToLoginCustomerPage(){
        return "logincustomer";
    }

    @GetMapping("/klant-worden")
    public String goToSignUp(){
        return "signup";
    }

    @GetMapping("/personeel")
    public String goToLoginEmployee(){
        return "loginemployee";
    }


}
