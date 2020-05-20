package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Address;
import nl.team2.parque_banque_server.model.Customer;
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

    @GetMapping("/confirmsignup")
    public ModelAndView correctFormHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView("signup");
        System.out.println(signUpFormBean);
        mav.addObject("form", signUpFormBean);
        return mav;
    }

    @PostMapping(value = "/bevestig", params = "action=send")
    public ModelAndView confirmSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("createlogin");
//        mav.addObject(signUpFormBean);
        CreateLoginFormBean createLoginFormBean = new CreateLoginFormBean();
        createLoginFormBean.setBsn(signUpFormBean.getBsn());
        mav.addObject(createLoginFormBean);

        // TODO: create new customer object and save in database
        Address address = new Address(signUpFormBean.getStreet(), signUpFormBean.getNumber(),
                signUpFormBean.getAddition(), signUpFormBean.getZipcode(), signUpFormBean.getCity());
        Customer customer = new Customer(signUpFormBean.getLastName(), signUpFormBean.getFirstName(),
                signUpFormBean.getInfix(), signUpFormBean.getPhone(), signUpFormBean.getEmailAddress(), address);
        customer.setBsn(signUpFormBean.getBsn());
        mav.addObject(customer);

        System.out.println("Confirmsignupcontroller: " + customer);

        return mav;
    }

//    @RequestParam(value="action") String action
    @PostMapping(value = "/bevestig", params = "action=edit")
    public ModelAndView editSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("signup");
        mav.addObject("form", signUpFormBean);

        return mav;
    }

//    @PostMapping("/confirmsignup")
//    public String confirmSignupHandler(@ModelAttribute SignUpFormBean signUpFormBean) {
//        return "accountview";
//    }
}
