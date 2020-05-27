package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/contact")
    public String goToContactPage() {
        return "contact";
    }

    @GetMapping("/rekening-openen")
    public String goToNewPaymentAccount(Model model) {
        if (model.containsAttribute("customerId")) {
            return "newpaymentaccount";
        } else {
            return "logincustomer";
        }
    }
}
