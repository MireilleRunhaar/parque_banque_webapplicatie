package nl.team2.parque_banque_server.controller;


import nl.team2.parque_banque_server.service.TransactionService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConfirmTransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value="/overboeken", params = "action:send")
    public String sendTransactionHandler(@ModelAttribute TransactionFormBean transactionFormBean){
        return "accountview";
    }

    @PostMapping(value = "/overboeken", params = "action:back")
    public String backFromConfirmationHandler(){
        return "newtransaction";
    }


}
