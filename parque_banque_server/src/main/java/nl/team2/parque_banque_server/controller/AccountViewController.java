package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.BusinessAccountService;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.service.PrivateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("customerId")
public class AccountViewController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PrivateAccountService privateAccountService;
    @Autowired
    private BusinessAccountService businessAccountService;
    @Autowired
    private PaymentAccountService paymentAccountService;


//in de getmapping
    @GetMapping("/rekening-overzicht")
    public String showAccountView(Model model){
        if(!model.containsAttribute("customerId")){
            return "logincustomer";
        }
        else {
            //Get the customer from the customerId
            /** Zoek de privateaccounts op op basis van de customerList
             *
             */
            Customer customer = customerService.findCustomerBySAId (model.getAttribute("customerId"));
            List <PrivateAccount> privateAccountList = privateAccountService.getPrivateAccountsByCustomer(customer);
            List<BusinessAccount> businessAccountList=businessAccountService.getBusinessAccountsByCustomer(customer);
            model.addAttribute("customer", customer);
            model.addAttribute("firstName", customer.getFirstName());
            model.addAttribute("affix", customer.getAffix());
            model.addAttribute("surName", customer.getSurName());
            model.addAttribute("privateaccounts", privateAccountList);
            model.addAttribute("businessaccounts", businessAccountList);
            return "accountview";
        }

    }

//request for opening account from accountview.html
    @GetMapping("/rekening-overzicht/rekening-openen")
    public ModelAndView newPaymentAccountHandler(){
        ModelAndView mav = new ModelAndView("newpaymentaccount");
        return mav;
    }

    //request for changing account
    @GetMapping("/rekening-overzicht/wijzig-gegevens")
    public ModelAndView changeDataHandler(){
        ModelAndView mav = new ModelAndView("changedata");
        return mav;
    }
    //request for signout
    @GetMapping("/rekening-overzicht/uitloggen")
    public ModelAndView logOutHandler(){
        ModelAndView mav = new ModelAndView("homepage");
        return mav;
    }
//    //request for connectaccount
//    @GetMapping("/rekening-toevoegen")
//    public ModelAndView connectaccountHandler(){
//    ModelAndView mav = new ModelAndView("linkpaymentaccount");
//    return mav;
//    }

}
