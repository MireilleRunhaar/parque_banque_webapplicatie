package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/contact")
    public String goToContactPage(){
        return "contact";
    }

    @GetMapping("/rekening-openen")
    public String goToNewPaymentAccount(){
        return "newpaymentaccount";
    }
}
