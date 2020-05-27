package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId")
public class NewPrivateAccountController {

    public final long START_SALDO=0;

    private PaymentAccountService.IbanService ibanService;
    private PaymentAccountService paymentAccountService;


    @Autowired
    public NewPrivateAccountController(PaymentAccountService.IbanService ibanService, PaymentAccountService paymentAccountService) {
        this.ibanService = ibanService;
        this.paymentAccountService = paymentAccountService;
    }

    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(){
        ModelAndView mav=new ModelAndView("confirmprivateaccount");
        PrivateAccount privateAccount=new PrivateAccount(ibanService.createNewIban(),START_SALDO);
        paymentAccountService.savePrivateAccount(privateAccount);
        mav.addObject("iban",privateAccount.getIban());
        mav.addObject("balanceCent",privateAccount.getBalanceCent());
        return mav;
    }

}
