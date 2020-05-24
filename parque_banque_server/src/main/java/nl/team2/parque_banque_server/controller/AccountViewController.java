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
//request for opening account from accountview.html
    @GetMapping("/rekening-overzicht/rekening-openen")
    public ModelAndView newPaymentAccountHandler(){
        ModelAndView mav = new ModelAndView("newpaymentaccount");
        return mav;
    }

    //request for changing account
    @GetMapping("/rekening-overzicht/wijzig-gegevens")
    public ModelAndView changeDataHandler(){
        ModelAndView mav = new ModelAndView("changedata");
        return mav;
    }
    //request for signout
    @GetMapping("/rekening-overzicht/uitloggen")
    public ModelAndView logOutHandler(){
        ModelAndView mav = new ModelAndView("homepage");
        return mav;
    }
    //request for connectaccount
    @GetMapping("/rekening-overzicht/connectaccount")
    public ModelAndView connectaccountHandler(){
    ModelAndView mav = new ModelAndView("connectaccount");
    return mav;
    }

}
