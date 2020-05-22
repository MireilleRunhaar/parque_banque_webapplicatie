package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.services.SignUpServices;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CreateLoginController {

    public CreateLoginController() {
        super();
    }


    @PostMapping(value = "/klant-worden", params = "action=finish")
    public ModelAndView sendLoginCredentialsHandler(@Valid CreateLoginFormBean createLoginFormBean,
                                                    BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("createlogin");
        } else {
            SignUpServices.saveNewCustomer(createLoginFormBean);
            mav.setViewName("redirect:/rekeningoverzicht");
        }

        return mav;
    }

}
