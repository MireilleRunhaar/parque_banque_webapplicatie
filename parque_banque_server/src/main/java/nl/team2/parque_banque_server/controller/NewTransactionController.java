package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@SessionAttributes({"customerId", "iban", "transactionFormBean"})
public class NewTransactionController {

    public static final int MIN_AMOUNT = 1;
    @Autowired
    PaymentAccountService paymentAccountService;

    @GetMapping("/overboeken")
    public String newTransactionHandler(Model model){
        if(!model.containsAttribute("customerId")){
            return "logincustomer";
        }else {
            model.addAttribute("transactionFormBean", new TransactionFormBean());
            return "newtransaction";
        }
    }

    // TODO: 05/06/2020 validaties voor gebruiker zijn nog niet mooi, afmaken.
    @PostMapping("/overboeken")
    public String transactionHandler(@Valid @ModelAttribute("transactionFormBean") TransactionFormBean transactionFormBean,
                                     BindingResult bindingResult, Model model){

        String ibanDebitAccount = (String) model.getAttribute("iban");
        if(bindingResult.hasErrors() || transactionFormBean.getTotalAmountInCents() < MIN_AMOUNT){
            return "newtransaction";
        } else {
            model.addAttribute("transactionFormBean",transactionFormBean);
            model.addAttribute("iban", ibanDebitAccount);
            return "confirmtransaction";
        }
    }

    @CrossOrigin
    @PostMapping("saldo-check")
    public @ResponseBody boolean checkSaldo(@RequestParam("transactionAmount") String transactionAmount, Model model){
        String ibanDebitAccount = (String) model.getAttribute("iban");
        long ta = Long.parseLong(transactionAmount);
        return paymentAccountService.validateFunds(ibanDebitAccount, ta);
    }

    @CrossOrigin
    @PostMapping("iban-check")
    public @ResponseBody boolean checkIbanExcist(@RequestParam("ibanCredit") String ibanCredit){
        return paymentAccountService.findOneByIban(ibanCredit) != null;
    }

}
