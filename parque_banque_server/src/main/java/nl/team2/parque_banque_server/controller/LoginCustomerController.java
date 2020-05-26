package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("customerId")
public class LoginCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/inloggen")
    public ModelAndView loginHandler(@ModelAttribute LoginCustomerFormBean loginCustomerFormBean) {
        ModelAndView mav = new ModelAndView("logincustomer");
        mav.addObject("customer", loginCustomerFormBean);
        return mav;
    }

    @PostMapping("/inloggen")
    public ModelAndView customerLoginFormHandler(@Valid LoginCustomerFormBean loginCustomerFormBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors() || !loginService.customerLoginValidation(loginCustomerFormBean)){
            mav.addObject("invalidCredentials", true);
            mav.setViewName("logincustomer");
        } else {
            Customer customer = customerService.findByUserName(loginCustomerFormBean.getUserName());
            mav.addObject("customerId", customer.getId());
            mav.setViewName("accountview");
        }
        return mav;
    }
}
