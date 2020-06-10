package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Authorisation;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.service.AuthorisationService;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LinkAccountService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.utilities.AddAccountHolderFormBean;
import nl.team2.parque_banque_server.utilities.LinkAccountFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("customerId")
public class LinkAccountController {

    @Autowired
    private AuthorisationService authorisationService;
    @Autowired
    private LinkAccountService linkAccountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentAccountService paymentAccountService;

    //controleer of de customer is ingelogd. Zo nee, ga dan naar de login pagina.
    //if customer is logged in, then fill iban and safetycode in.
    //get authorisation object by username
    //check if iban and authorisatiecode are the same as the ones that the customer has filled.
    @GetMapping("/rekening-koppelen")
    public String linkAccountHandler(Model model) {
        if (model.getAttribute("customerId") != null) {
            model.addAttribute(new LinkAccountFormBean());
            return "linkpaymentaccount";
        } else {
            return "logincustomer";
        }

    }

    //checking for login credentials
    @PostMapping("/rekening-koppelen")
    public ModelAndView linkAccount(@Valid LinkAccountFormBean linkAccountFormBean, BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();
        System.out.println("dit is de postmapping");
        //input voor linkaccountvalidation is linkaccountformbean en addaccountholderformbean.
        if (bindingResult.hasErrors()) {
            System.out.println("fouten");
            model.addAttribute("invalidCredentials", true);
            mav.setViewName("linkpaymentaccount");
        } else {
            //alsgevoegd omdat id anders niet goed gaat.
            Customer customer = customerService.findCustomerBySAId(model.getAttribute("customerId"));
            if (linkAccountService.linkAccountValidation(linkAccountFormBean, customer.getUserName())) {
                PaymentAccount paymentAccount = paymentAccountService.findOneByIban(linkAccountFormBean.getIban());
                paymentAccount.addCustomerToAccountHolder(customer);
                paymentAccountService.savePaymentAccount(paymentAccount);
                mav.setViewName("linkpaymentaccountconfirmation");
            }
            else {
                System.out.println("verkeerd wachtwoord");
                model.addAttribute("invalidCredentials", true);
                mav.setViewName("linkpaymentaccount");
            }
        }
        return mav;
    }

}
