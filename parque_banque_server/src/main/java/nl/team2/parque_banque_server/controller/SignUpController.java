package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public ModelAndView signupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        // add user object
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("form", signUpFormBean);
//        SignUpFormBean signUpFormBean = new SignUpFormBean();
//        signUpFormBean.setFirstName("Machiel");
//        model.addAttribute(signUpFormBean);
//        return "signup";
        return mav;
    }

//    @PostMapping("/confirmsignup")
//    public ModelAndView sendSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
//        ModelAndView mav = new ModelAndView("confirmsignup");
//
//        mav.addObject("form", signUpFormBean);
//        return mav;
////        model.addAttribute(signUpFormBean);
////        return "confirmsignup";
//    }

    @PostMapping("/signup")
    public ModelAndView sendSignupHandler(@Valid SignUpFormBean signUpFormBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        System.out.println("error? : " + bindingResult.toString());
        if (bindingResult.hasErrors()) {
            mav.setViewName("signup");
            return mav;
        } else {
            mav.setViewName("confirmsignup");

            mav.addObject("form", signUpFormBean);
            return mav;
        }
//        model.addAttribute(signUpFormBean);
//        return "confirmsignup";
    }
}
