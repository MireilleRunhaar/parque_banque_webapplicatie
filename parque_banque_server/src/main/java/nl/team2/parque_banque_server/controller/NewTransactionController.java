package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Transaction;
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

    @GetMapping("/overboeken")
    public String newTransactionHandler(Model model){
        model.addAttribute("transactionFormBean", new TransactionFormBean());
        return "newtransaction";
    }

    @PostMapping(value= "/overboeken", params= "action:next")
    public String transactionHandler(@ModelAttribute @Valid TransactionFormBean transactionFormBean,
                                     BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            // TODO: 05/06/2020 Hier is het mooier als je dan een volledig ingevuld en aanpasbaar formulier krijgt
            return "newtransaction";
        } else if(!transactionService.validateIBANExcists()){
            // TODO: 05/06/2020 Regel op pagina dat IBAN niet bekend is
            return "newtransaction";
        } else if (!transactionService.validateSufficientFunds()){
            // TODO: 05/06/2020 Regel op pagina dat saldo onvoldoende is
            return "newtransaction";
        } else{
            Transaction transaction = transactionFormBean.createTransaction();
           model.addAttribute("transactionInput",transaction);
            return "confirmtransaction";
        }
    }

}
