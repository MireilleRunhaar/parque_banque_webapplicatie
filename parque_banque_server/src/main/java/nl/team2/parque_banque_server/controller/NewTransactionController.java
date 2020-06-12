package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Lisa Kemeling
 * Controller for receiving and handling transactionrequests
 * Transaction input is validated and send for confirmation
 */


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


    @PostMapping("/overboeken")
    public String transactionHandler(@Valid @ModelAttribute("transactionFormBean") TransactionFormBean transactionFormBean,
                                     BindingResult bindingResult, Model model){

        String ibanDebitAccount = (String) model.getAttribute("iban");
        if(bindingResult.hasErrors() || !paymentAccountService.validateFunds(ibanDebitAccount, transactionFormBean.getTotalAmountInCents())) {
            return "newtransaction";
        } else {
            model.addAttribute("transactionFormBean",transactionFormBean);
            model.addAttribute("iban", ibanDebitAccount);
            return "confirmtransaction";
        }
    }

    @CrossOrigin
    @PostMapping("saldo-check")
    public @ResponseBody boolean checkSaldo(@RequestParam("transactionAmount") long transactionAmount, Model model){
        String ibanDebitAccount = (String) model.getAttribute("iban");
        return paymentAccountService.validateFunds(ibanDebitAccount, transactionAmount);
    }

    @CrossOrigin
    @PostMapping("iban-check")
    public @ResponseBody boolean checkIbanExcist(@RequestParam("ibanCredit") String ibanCredit){
        return paymentAccountService.findOneByIban(ibanCredit) != null;
    }

}
