package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.AuthorisationService;
import nl.team2.parque_banque_server.service.LinkAccountService;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("customerId")
public class LinkAccountController {

    @Autowired
    private AuthorisationService authorisationService;
    @Autowired
    private LinkAccountService linkAccountService;

    //controleer of de customer is ingelogd. Zo nee, ga dan naar de login pagina.
    //if customer is logged in, then fill iban and safetycode in.
    //get authorisation object by username
    //check if iban and authorisatiecode are the same as the ones that the customer has filled.
    @GetMapping("/rekening-toevoegen")
    public String linkAccountHandler(@ModelAttribute LinkAccountFormBean linkAccountFormBean, Model model) {
        if (model.getAttribute("customerId") != null) {
            return "linkpaymentaccount";
        } else {
            return "logincustomer";
        }

    }

    //checking for login credentials
    @PostMapping("/rekening-toevoegen")
    public String linkAccount(@Valid LinkAccountFormBean linkAccountFormBean, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || !linkAccountService.linkAccountValidation(linkAccountFormBean)) {
            model.addAttribute("invalidCredentials", true);
            return "linkpaymentaccount";
        } else {
            Authorisation authorisation = authorisationService.findAuthorisationByIban(linkAccountFormBean.getIban());
            model.addAttribute("customerId", authorisation.getId());
            return "redirect:/linkpaymentaccountconfirmation";

        }

    }
}