package nl.team2.parque_banque_server.controller;


import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.service.TransactionService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"ibanDebitAccount", "transactionFormBean"})
public class ConfirmTransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value="/overboeken", params = "action=send")
    public String sendTransactionHandler(Model model){
        TransactionFormBean transactionFormBean = (TransactionFormBean)model.getAttribute("transactionFormBean");
        String ibanDebitAccount = (String) model.getAttribute("ibanDebitAccount");
       if(transactionFormBean == null){
           return "error";
       }
        Transaction transaction = transactionService.createTransactionFromBean(transactionFormBean,ibanDebitAccount);
        transactionService.executeAndSave(transaction);
        return "index";
    }

    @PostMapping(value = "/overboeken", params = "action=back")
    public String backFromConfirmationHandler(@ModelAttribute TransactionFormBean transactionFormBean, Model model){
        model.addAttribute("transactionFormBean", transactionFormBean);
        return "newtransaction";
    }


}
