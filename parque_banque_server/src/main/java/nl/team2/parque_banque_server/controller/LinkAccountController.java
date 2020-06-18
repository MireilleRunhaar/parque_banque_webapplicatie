package nl.team2.parque_banque_server.controller;

import com.sun.xml.bind.v2.TODO;
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
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/rekening-koppelen")
    public String linkAccountHandler(Model model) {
        if (model.getAttribute("customerId") != null) {
            model.addAttribute(new LinkAccountFormBean());
            return "linkpaymentaccount";
        } else {
            return "logincustomer";
        }

    }

    /**
     * Links customer to an other account. If the customer is already linked then the app gives a messages.
     * @param linkAccountFormBean
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/rekening-koppelen")
    public ModelAndView linkAccount(@Valid LinkAccountFormBean linkAccountFormBean, BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.addAttribute("invalidCredentials", true);
            mav.setViewName("linkpaymentaccount");
        } else {
            Customer customer = customerService.findCustomerBySAId(model.getAttribute("customerId"));
            if(paymentAccountService.checkAccount(linkAccountFormBean, customer)){
                model.addAttribute("rekeningGekoppeld", true);
                mav.setViewName("linkpaymentaccount");
            }
            else if (linkAccountService.linkAccountValidation(linkAccountFormBean, customer.getUserName())) {
                PaymentAccount paymentAccount = paymentAccountService.findOneByIban(linkAccountFormBean.getIban());
                paymentAccount.addCustomerToAccountHolder(customer);
                paymentAccountService.savePaymentAccount(paymentAccount);
                model.addAttribute("iban", linkAccountFormBean.getIban());
                mav.setViewName("linkpaymentaccountconfirmation");
            }
            else {
                model.addAttribute("invalidCredentials", true);
                mav.setViewName("linkpaymentaccount");
            }
        }
        return mav;
    }

    @CrossOrigin
    @PostMapping("/check-Iban")
    public @ResponseBody boolean checkIban (@RequestParam("iban") String iban){
               return paymentAccountService.findOneByIban(iban) != null;
    }


   }
