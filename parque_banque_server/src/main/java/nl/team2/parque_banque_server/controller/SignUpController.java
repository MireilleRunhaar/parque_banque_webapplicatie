package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String signupHandler(Model model) {
        // add user object
        SignUpFormBean signUpFormBean = new SignUpFormBean();
        model.addAttribute(signUpFormBean);

        return "signup";
    }

    @PostMapping("/signup")
    public String signupConfirmation(@ModelAttribute SignUpFormBean signUpFormBean, Model model) {
        model.addAttribute(signUpFormBean);
        return "confirmsignup";
    }
}
