package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewPaymentAccountController {

    public NewPaymentAccountController() {
    }

    @GetMapping("rekeningopenen")
    public String newPaymentAccount(){
        return "newpaymentaccount";
    }

    @GetMapping("particuliererekening")
    public String newPrivateAccount(){
        return "newprivateaccount";
    }

  /*  @GetMapping("zakelijkerekening")
    public String newCompanyAccount(){
        return "newcompanyaccount";
    }*/
}


