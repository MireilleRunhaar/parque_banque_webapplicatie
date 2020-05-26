package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.IbanService;
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

    private IbanService ibanService;
    private PrivateAccountService privateAccountService;

    @Autowired
    public NewPrivateAccountController(IbanService ibanService, PrivateAccountService privateAccountService) {
        this.ibanService = ibanService;
        this.privateAccountService = privateAccountService;
    }


    @GetMapping("/rekening-openen")
    public String goToNewPaymentAccount(){
        return "newpaymentaccount";
    }


    @PostMapping("/particuliere-rekening-openen")
    public ModelAndView createNewPrivateAccount(@ModelAttribute CreatePrivateAccountBackingBean bb){
        ModelAndView mav=new ModelAndView("confirmprivateaccount");
        PrivateAccount privateAccount=new PrivateAccount(ibanService.createNewIban(),0);
        //PrivateAccount privateAccount=bb.privateAccount();
        privateAccountService.savePrivateAccount(privateAccount);
        mav.addObject("iban",privateAccount.getIban());
        mav.addObject("balanceCent",privateAccount.getBalanceCent());
        return mav;
    }

}
