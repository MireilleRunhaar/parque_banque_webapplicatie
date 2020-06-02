package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.service.PrivateAccountService;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("customerId")
public class LoginCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PrivateAccountService privateAccountService;

    @GetMapping("/inloggen")
    public String loginHandler(@ModelAttribute LoginCustomerFormBean loginCustomerFormBean,
                               Model model) {
        if (model.containsAttribute("customerId") && loginCustomerFormBean == null) {
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
            model.addAttribute("firstName", customer.getFirstName());
            model.addAttribute("affix", customer.getAffix());
            model.addAttribute("surName",customer.getSurName());
//            List<PaymentAccount> paymentAccountList= customer.getPaymentAccounts();
            List<PrivateAccount> privateAccountList=privateAccountService.getPrivateAccountsByCustomer(customer);
            model.addAttribute("privateaccounts", privateAccountList);
            return "accountview";
        }
    }
}
