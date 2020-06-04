package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId")
public class NewPaymentAccountController {

    public NewPaymentAccountController() {
    }

    @GetMapping("/particuliere-rekening-openen")
    public String newPrivateAccount(Model model) {
        if(model.containsAttribute("customerId")){
            return "newprivateaccount";
        } else {
            return "logincustomer";
        }

    }

}


