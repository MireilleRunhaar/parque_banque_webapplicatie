package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("form")
public class ConfirmSignUpController {

    public ConfirmSignUpController() {
        super();
    }

    // If user is happy with the input data, user is sent to page for creating login credentials
    @PostMapping(value = "/klant-worden", params = "action=createaccount")
    public ModelAndView confirmSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("createlogin");


        mav.addObject(new CreateLoginFormBean());

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
