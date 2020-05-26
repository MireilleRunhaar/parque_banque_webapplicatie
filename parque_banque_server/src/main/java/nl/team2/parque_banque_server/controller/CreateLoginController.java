package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.SignUpServices;
import nl.team2.parque_banque_server.utilities.CreateLoginFormBean;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("form")
public class CreateLoginController {

    private final CustomerService customerService;

    public CreateLoginController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }


    // TODO: validate that username does not exist
    // If user finishes signup, create user object, save to database and send user to account view
    @PostMapping(value = "/klant-worden", params = "action=finish")
    public ModelAndView sendLoginCredentialsHandler(@Valid CreateLoginFormBean createLoginFormBean,
                                                    BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("createlogin");
        } else {
            SignUpFormBean signUpFormBean = (SignUpFormBean) model.getAttribute("form");
            // TODO: throw catch block with error page?
            assert signUpFormBean != null;
            // Create customer object and save to the database; redirect user to account view
            Customer customer = SignUpServices.createNewCustomer(signUpFormBean, createLoginFormBean);
            customerService.saveCustomer(customer);
            mav.setViewName("redirect:/rekeningoverzicht");
        }

        return mav;
    }

}