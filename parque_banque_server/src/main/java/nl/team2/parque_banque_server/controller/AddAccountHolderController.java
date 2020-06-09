package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.service.AddAccountHolderService;
import nl.team2.parque_banque_server.service.AuthorisationService;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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

    @PostMapping(value = "/rekeninghouder-toevoegen", params = "action=finish")
    public ModelAndView inputFormHandler(@Valid AddAccountHolderFormBean addAccountHolderFormBean,
                                         BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("addaccountholder/addaccountholder");
        } else if (addAccountHolderService.isInsecureCode(addAccountHolderFormBean.getSecurityCode())) {
            mav.setViewName("addaccountholder/addaccountholder");
            mav.addObject("insecureCode", true);
        } else {
            Authorisation authorisation = addAccountHolderService.createAuthorisation(addAccountHolderFormBean,
                    (String) model.getAttribute("iban"));
            authorisationService.saveAuthorisation(authorisation);
            mav.addObject(authorisation);
            mav.setViewName("addaccountholder/addaccountholderconfirmation");
        }
        return mav;
    }
}
