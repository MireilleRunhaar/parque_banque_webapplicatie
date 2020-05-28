package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("customerId")
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
