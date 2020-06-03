package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.service.SignUpService;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("form")
public class SignUpController {

    @GetMapping("/klant-worden")
    public ModelAndView signupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("form", signUpFormBean);

        return mav;
    }


    // If input form has no violations, send user to next page asking for confirmation of input data
    @PostMapping("/klant-worden")
    public ModelAndView sendSignupHandler(@Valid SignUpFormBean signUpFormBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("signup");
        } else if (!SignUpService.passesElfproef(signUpFormBean.getBsn())) {
            mav.setViewName("signup");
            mav.addObject("invalidBsn", true);
        } else {
            mav.setViewName("confirmsignup");

            // Capitalize the appropriate fields in the signUpFormBean
            SignUpFormBean formattedSignUpFormBean = SignUpService.formatFormInput(signUpFormBean);

            mav.addObject("form", formattedSignUpFormBean);
        }
        return mav;
    }

    // If user changes their mind and presses cancel button, return to homepage
    @PostMapping(value = "/klant-worden", params = "action=cancel")
    public String cancelLoginCredentialsHandler() {
        return "redirect:/";
    }
}
