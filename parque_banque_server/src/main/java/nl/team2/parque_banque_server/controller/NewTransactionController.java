package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.service.TransactionService;
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
 * Transaction input is validated and send after confirmation
 */


@Controller
@SessionAttributes({"customerId", "iban"})
public class NewTransactionController {


    @Autowired
    PaymentAccountService paymentAccountService;
    @Autowired
    TransactionService transactionService;


    @GetMapping("/overboeken")
    public String newTransactionHandler(Model model){
        if(!model.containsAttribute("customerId")){
            return "logincustomer";
        }else {
            model.addAttribute("transactionFormBean", new TransactionFormBean());
            String iban = model.getAttribute("iban").toString();
            model.addAttribute("ibanString", iban );
            return "newtransaction";
        }
    }


    @PostMapping("/overboeken")
    public String transactionHandler(@Valid @ModelAttribute("transactionFormBean") TransactionFormBean transactionFormBean,
                                     BindingResult bindingResult, Model model) {

        String ibanDebitAccount = (String) model.getAttribute("iban");
        if (bindingResult.hasErrors()) {
            return "newtransaction";
        } else if (!paymentAccountService.validateFunds(ibanDebitAccount, transactionFormBean.getTotalAmountInCents())) {
            model.addAttribute("insufficientFunds", true);
            return "newtransaction";
        } else if(paymentAccountService.findOneByIban(transactionFormBean.getIbanCreditAccount()) == null){
            model.addAttribute("ibanUnknown", true);
            return "newtransaction";
        } else{
            Transaction transaction = transactionService.createTransactionFromBean(transactionFormBean, ibanDebitAccount);
            try {
                transactionService.executeAndSave(transaction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return "error";
            }
            return "redirect:/rekening-overzicht/details" + ibanDebitAccount;
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
