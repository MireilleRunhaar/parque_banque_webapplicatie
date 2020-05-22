package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Address;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.services.SignUpServices;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmSignUpController {

    public ConfirmSignUpController() {
        super();
    }

    // If user is happy with the input data, user is sent to page for creating login credentials
    @PostMapping(value = "/klant-worden", params = "action=send")
    public ModelAndView confirmSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("createlogin");

        // Copy data to new createLoginFormBean (to enable new check on username & password)
        CreateLoginFormBean createLoginFormBean = SignUpServices.copyToLoginFormBean(signUpFormBean);

        mav.addObject(createLoginFormBean);

        return mav;
    }

    // If user wants to edit the form, user is sent back to previous page.
    @PostMapping(value = "/klant-worden", params = "action=edit")
    public ModelAndView editSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("signup");
        mav.addObject("form", signUpFormBean);

        return mav;
    }
}
