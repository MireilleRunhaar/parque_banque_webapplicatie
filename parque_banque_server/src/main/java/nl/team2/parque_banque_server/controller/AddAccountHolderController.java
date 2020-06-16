package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.service.AddAccountHolderService;
import nl.team2.parque_banque_server.service.AuthorisationService;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes({"customerId", "iban"})
public class AddAccountHolderController {

    @Autowired
    private AddAccountHolderService addAccountHolderService;
    @Autowired
    private AuthorisationService authorisationService;

    @GetMapping("/rekeninghouder-toevoegen")
    public ModelAndView addAccountHolderHandler(Model model) {
        ModelAndView mav = new ModelAndView();
        if (model.containsAttribute("customerId")) {
            mav.setViewName("addaccountholder/addaccountholder");
            mav.addObject(new AddAccountHolderFormBean());
        } else {
            mav.setViewName("redirect:/inloggen");
        }
        return mav;
    }



    @CrossOrigin
    @PostMapping("veilige-code")
    public @ResponseBody
    boolean securityCodeCheckHandler(@RequestParam("securityCode") String securityCode) {
        return !addAccountHolderService.isInsecureCode(securityCode);
    }

    @CrossOrigin
    @PostMapping("nieuwe-rekeninghouder")
    public @ResponseBody
    boolean checkCustomerIsNewHandler(@RequestParam("username") String username, Model model) {
        return !addAccountHolderService.customerAlreadyAccountHolder(username, (String) model.getAttribute("iban"));
    }

    @CrossOrigin
    @PostMapping("authorisatie-opslaan")
    public @ResponseBody
    Authorisation saveAuthorisationHandler(@RequestParam("username") String username, @RequestParam("code") String securityCode,
                                           @RequestParam("iban") String iban) {
        Authorisation authorisation = addAccountHolderService.authorisationInDatabase(username, securityCode, iban);
        if (authorisation == null) {
            authorisation = addAccountHolderService.createAuthorisation(username, securityCode, iban);
            authorisationService.saveAuthorisation(authorisation);
        }
        return authorisation;
    }
}
