package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.utilities.CreatePrivateAccountBackingBean;
import nl.team2.parque_banque_server.service.PrivateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewPrivateAccountController {

    @Autowired
    private PrivateAccountService privateAccountService;

    @GetMapping("/rekening-openen")
    public String goToNewPaymentAccount(){
        return "newpaymentaccount";
    }


    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(@ModelAttribute CreatePrivateAccountBackingBean bb){
        ModelAndView mav=new ModelAndView("confirmprivateaccount");
        PaymentAccount privateAccount=new PrivateAccount();
        privateAccountService.savePrivateAccount(privateAccount);
        mav.addObject("iban",privateAccount.getIban()); //nieuwe defeaultwaarde
        mav.addObject("balanceCent",privateAccount.getBalance()); //balanceCent ?
        return mav;
    }

}
