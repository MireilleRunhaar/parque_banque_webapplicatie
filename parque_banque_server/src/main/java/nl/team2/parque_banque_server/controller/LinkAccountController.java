package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("customerId")
public class LinkAccountController {

    //controleer of de customer is ingelogd. Zo nee, ga dan naar de login pagina.
    //if customer is logged in, then fill iban and safetycode in.
    //get authorisation object by username
    //check if iban and authorisatiecode are the same as the ones that the customer has filled.
    @GetMapping("/rekening-toevoegen")
    public String linkAccountHandler(Model model){
        Authorisation authorisation = new Authorisation();
        model.addAttribute("authorisation", authorisation);


    @PostMapping("/rekening-toevoegen")
    public String processLogin (@Valid @ModelAttribute("authorisation") Authorisation authorisation, Model model){
        return "bevestiging-rekening-toevoegen";
        }
    }

}
