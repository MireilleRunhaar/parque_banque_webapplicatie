package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewPrivateAccountController {


    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(){
        ModelAndView mav=new ModelAndView("confirmprivateaccount");
        return mav;
    }

}
