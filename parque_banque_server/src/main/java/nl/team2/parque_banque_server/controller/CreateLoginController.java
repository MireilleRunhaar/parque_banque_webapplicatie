package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CreateLoginController {

    public CreateLoginController() {
        super();
    }

    @GetMapping("/accountmaken")
    public String createLoginHandler(@ModelAttribute SignUpFormBean signUpFormBean, Customer customer) {
        System.out.println(customer);
        System.out.println(signUpFormBean);
        return "createlogin";
    }

    @PostMapping("/accountview")
    public ModelAndView sendLoginCredentialsHandler(@Valid CreateLoginFormBean createLoginFormBean,
                                                    BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("accountview");

        System.out.println(createLoginFormBean);
        //TODO create user
        return mav;
    }

}
