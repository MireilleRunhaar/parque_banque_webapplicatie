package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.*;
import nl.team2.parque_banque_server.utilities.AccountViewListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    private AccountViewService accountViewService;


    @GetMapping("/rekening-overzicht")
    public String showAccountView(Model model){
        if (!model.containsAttribute("customerId")) {
            return "logincustomer";
        } else {
            Customer customer = customerService.findCustomerBySAId (model.getAttribute("customerId"));
            List<PrivateAccount> privateAccountList = privateAccountService.getPrivateAccountsByCustomer(customer);
            List<BusinessAccount> businessAccountList = businessAccountService.getBusinessAccountsByCustomer(customer);

            //Convert long balanceCent to String balanceEuros with a bean.
            List<AccountViewListBean> privateAccountViewList = accountViewService.convertPrivateAccountList(privateAccountList);
            List<AccountViewListBean> businessAccountViewList = accountViewService.convertBusinessAccountList(businessAccountList);

            model.addAttribute("customer", customer);
            model.addAttribute("privateaccounts", privateAccountViewList);
            model.addAttribute("businessaccounts", businessAccountViewList);
            return "accountview";
        }
    }

    //request for opening account from accountview.html
    @GetMapping("/rekening-overzicht/rekening-openen")
    public ModelAndView newPaymentAccountHandler(){
        return new ModelAndView("newpaymentaccount");
    }

    //request for changing account
    @GetMapping("/rekening-overzicht/wijzig-gegevens")
    public ModelAndView changeDataHandler(){
        return new ModelAndView("changedata");
    }

    //request for signout
    @GetMapping("/rekening-overzicht/uitloggen")
    public ModelAndView logOutHandler(){
        return new ModelAndView("index");
    }

    //request for connectaccount
    @GetMapping("/rekening-overzicht/connectaccount")
    public ModelAndView connectaccountHandler(){
        return new ModelAndView("connectaccount");
    }

}
