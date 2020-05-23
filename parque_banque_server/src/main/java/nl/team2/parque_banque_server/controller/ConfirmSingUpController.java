package nl.team2.parque_banque_server.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class ConfirmSingUpController {

    //Minke: pass the firstname, affix and surname on to page paymentAccount.html
    @PostMapping("/bevestig-klant-worden")//juiste naam meegeven
    public ModelAndView confirmSignUpHandler(
            @RequestParam String firstName,
            @RequestParam String affix,
            @RequestParam String surname) {
        ModelAndView mav = new ModelAndView("accountview");
        mav.addObject("firstname", firstName);
        mav.addObject("affix", affix);
        mav.addObject("surname", surname);
        return mav;

    }

}
