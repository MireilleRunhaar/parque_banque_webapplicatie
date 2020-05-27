package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.management.Attribute;
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

        if (model.containsAttribute("customerId")) {
            return "accountview";
        } else {
            model.addAttribute("customerId", loginCustomerFormBean);
            return "logincustomer";
        }
    }

    @PostMapping("/inloggen")
    public String customerLoginFormHandler(@Valid LoginCustomerFormBean loginCustomerFormBean,
                                                 BindingResult bindingResult,
                                                 Model model) {
        if (bindingResult.hasErrors() || !loginService.customerLoginValidation(loginCustomerFormBean)){
            model.addAttribute("invalidCredentials", true);
            return "logincustomer";
        } else {
            Customer customer = customerService.findByUserName(loginCustomerFormBean.getUserName());
            model.addAttribute("customerId", customer.getId());
            return "accountview";
        }
    }
}
