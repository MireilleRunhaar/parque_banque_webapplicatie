package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewPaymentAccountController {

    public NewPaymentAccountController() {
    }


    @GetMapping("/particuliere-rekening-openen")
    public String newPrivateAccount(){
        return "newprivateaccount";
    }

    @GetMapping("/zakelijke-rekening-openen")
    public String newCompanyAccount(){
        return "newcompanyaccount";
    }
}


