package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.SignUpService;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("signupform")
public class CreateLoginController {

    private final SignUpService signUpService;

    @Autowired
    public CreateLoginController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    // If user finishes signup, create user object, save to database and send user to login
    @PostMapping(value = "/klant-worden", params = "action=finish")
    public ModelAndView sendLoginCredentialsHandler(@Valid CreateLoginFormBean createLoginFormBean,
                                                    BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();

        // Check whether input contains errors, or whether username is taken; else save new customer
        if (bindingResult.hasErrors()) {
            mav.setViewName("createlogin");
        } else {
            SignUpFormBean signUpFormBean = (SignUpFormBean) model.getAttribute("signupform");
            if ( signUpFormBean != null ) {
                signUpService.saveNewCustomer(signUpFormBean, createLoginFormBean);
                mav.setViewName("redirect:/inloggen");
            } else {
                mav.setViewName("error");
            }
        }

        return mav;
    }

    @CrossOrigin
    @PostMapping("/username-controle")
    public @ResponseBody
    boolean usernameCheckHandler(@RequestParam("username") String username) {
        return signUpService.isUserNameTaken(username);
    }

}
