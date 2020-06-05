package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("customerId")
public class LoginCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/inloggen")
    public String loginHandler(@ModelAttribute LoginCustomerFormBean loginCustomerFormBean,
                               Model model) {
        // Without this if statement, the application doesn't know what to do when you arrive at this page after
        // you've visited a page with an "form" session attribute and left it empty (not filling in the form).
        if (model.containsAttribute("signupform")) {
            return "logincustomer";
        // Checks if the customerId is not empty, if that's the case the user will be redirected to the accountview
        } else if (model.getAttribute("customerId") != null) {
            return "redirect:/rekening-overzicht";
        } else {
            return "logincustomer";
        }
    }

    @PostMapping("/inloggen")
    public String customerLoginFormHandler(@Valid LoginCustomerFormBean loginCustomerFormBean,
                                                 BindingResult bindingResult,
                                                 Model model) {
        // Checks the bean validation of the input and if the login is valid.
        if (bindingResult.hasErrors() || !loginService.customerLoginValidation(loginCustomerFormBean)){
            model.addAttribute("invalidCredentials", true);
            return "logincustomer";
        } else {
            Customer customer = customerService.findByUserName(loginCustomerFormBean.getUserName());
            model.addAttribute("customerId", customer.getId());
            return "redirect:/rekening-overzicht";
        }
    }

    @GetMapping("/uitloggen")
    public String userLogOuthandler(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
