package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;


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
            PaymentAccount creditAccount = paymentAccountService.findOneByIban(transactionFormBean.getIbanCreditAccount());
            if(!paymentAccountService.validateFunds(ibanDebitAccount, transactionFormBean.getTotalAmountInCents())){
                model.addAttribute("insufficientFunds", true);
                return "newtransaction";
            } else if(creditAccount == null){
                model.addAttribute("invalidCreditAccount", true);
                return "newtransaction";
            }
            model.addAttribute("transactionFormBean",transactionFormBean);
            model.addAttribute("iban", ibanDebitAccount);
            return "confirmtransaction";
        }
    }
}
