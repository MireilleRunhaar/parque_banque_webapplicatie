package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.service.TransactionService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class NewTransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    PaymentAccountService paymentAccountService;

    @GetMapping("/overboeken")
    public String newTransactionHandler(Model model){
        model.addAttribute("ibanDebitAccount", "NL10PARQ0100000001");
        model.addAttribute("transactionFormBean", new TransactionFormBean());
        return "newtransaction";
    }

    // TODO: 05/06/2020 validaties tonen op scherm
    @PostMapping(value= "/overboeken", params= "action:next")
    public String transactionHandler(@ModelAttribute @Valid TransactionFormBean transactionFormBean,
                                     BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "newtransaction";
        } else{
            PaymentAccount creditAccount = paymentAccountService.findOneByIban(transactionFormBean.getIbanCreditAccount());
            PaymentAccount debitAccount = paymentAccountService.findOneByIban((String)model.getAttribute("ibanDebitAccount"));
            if(creditAccount == null){
                model.addAttribute("invalidCreditAccount", true);
                return "newtransaction";
            } else if(!debitAccount.validateSufficientBalance(transactionFormBean.getAmount())){
                model.addAttribute("unsufficientBalance", true);
                return "newtransaction";
            }
            model.addAttribute("transactionInput",transactionFormBean);
            return "confirmtransaction";
        }
    }
}
