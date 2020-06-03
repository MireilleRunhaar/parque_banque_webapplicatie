package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.service.PrivateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId") // key -value
public class NewPrivateAccountController {

    public final long START_SALDO=0;

    private PaymentAccountService.IbanService ibanService;
    private CustomerService customerService;
    private PrivateAccountService privateAccountService;
    private PaymentAccountService paymentAccountService;

    @Autowired
    public NewPrivateAccountController(PaymentAccountService paymentAccountService,PaymentAccountService.IbanService ibanService, CustomerService customerService, PrivateAccountService privateAccountService) {
        this.ibanService = ibanService;
        this.customerService = customerService;
        this.privateAccountService = privateAccountService;
        this.paymentAccountService = paymentAccountService;
    }




    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(Model model){
        ModelAndView mav=new ModelAndView("confirmnewaccount");
        long id= (long) model.getAttribute("customerId");
        Customer customer=customerService.findById(id);

        PrivateAccount privateAccount=new PrivateAccount(ibanService.createNewIban(),START_SALDO);
        privateAccount.addCustomerToAccountHolder(customer);

        privateAccountService.savePrivateAccount(privateAccount);

        mav.addObject("iban",privateAccount.getIban());
        mav.addObject("balanceCent",paymentAccountService.balanceInEuros(privateAccount.getBalanceCent()));
        mav.addObject("name", customer.getFirstName());
        mav.addObject("privateAccount", true);
        return mav;
    }

}
