package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId") // key-value paar
public class NewPrivateAccountController {

    public final long START_SALDO=0;

    private PaymentAccountService.IbanService ibanService;
    private PaymentAccountService paymentAccountService;
    private CustomerService customerService;

    @Autowired
    public NewPrivateAccountController(PaymentAccountService.IbanService ibanService,
                                       PaymentAccountService paymentAccountService,
                                       CustomerService customerService) {
        this.ibanService = ibanService;
        this.paymentAccountService = paymentAccountService;
        this.customerService = customerService;
    }


    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(Model model ){
        long id= (long) model.getAttribute("customerId"); //geeft object terug
        Customer customer= customerService.findCustomerByCustomerId(id);

        ModelAndView mav=new ModelAndView("confirmprivateaccount");
        PrivateAccount privateAccount=new PrivateAccount(ibanService.createNewIban(),START_SALDO);
        privateAccount.addCustomerToAccountHolder(customer);

        paymentAccountService.savePrivateAccount(privateAccount);


        mav.addObject("iban",privateAccount.getIban());
        mav.addObject("balanceCent",privateAccount.getBalanceCent());
        //mav.addObject("accountHolder")

        return mav;
    }

}
