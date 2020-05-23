package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountViewController {

    @GetMapping("/rekening-overzicht")
    public ModelAndView accountViewHandler(){
        ModelAndView mav = new ModelAndView("accountview");
        return mav;
    }

    @GetMapping("/rekening-overzicht/rekening-openen")
    public ModelAndView newPaymentAccountHandler(){
        ModelAndView mav = new ModelAndView("newpaymentaccount");
        return mav;
    }

    //ook nog opnemen getmapping voor
//    - wijzigen gegevens, rekening koppelen
//


    //koppelen aan knoppen etc
}
