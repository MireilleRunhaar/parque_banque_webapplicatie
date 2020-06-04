package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId")
public class LinkAccountController {

    @GetMapping("/rekening-toevoegen")
    public ModelAndView linkAccountHandler(Model model, Authorisation authorisation){
        ModelAndView mav= new ModelAndView();
        return mav;
    }

}
