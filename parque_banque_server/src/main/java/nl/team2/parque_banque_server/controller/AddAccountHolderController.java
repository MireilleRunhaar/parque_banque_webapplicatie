package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("customerId")
public class AddAccountHolderController {

    @GetMapping("/rekeninghouder-toevoegen")
    public ModelAndView addAccountHolderHandler(Model model) {
        ModelAndView mav = new ModelAndView();
        // TODO: validate that user is logged in (remove rows below, uncomment code in comments)
        mav.setViewName("addaccountholder/addaccountholder");
        mav.addObject("addaccountholderform", new AddAccountHolderFormBean());
//        if (model.containsAttribute("customerId")) {
//            mav.setViewName("addaccountholder/addaccountholder");
//        } else {
//            mav.setViewName("redirect:/inloggen");
//        }
        return mav;
    }

    @PostMapping(value = "/rekeninghouder-toevoegen", params = "action=finish")
    public ModelAndView inputFormHandler(@Valid AddAccountHolderFormBean addAccountHolderFormBean,
                                         BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("addaccountholder/addaccountholder");
        } else {

        }
        return mav;
    }
}
