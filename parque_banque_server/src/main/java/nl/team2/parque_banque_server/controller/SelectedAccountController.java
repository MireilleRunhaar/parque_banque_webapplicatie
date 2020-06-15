package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.SelectedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"customerId", "iban"})
public class SelectedAccountController {

    @Autowired
    CustomerService customerService;

    @Autowired
    SelectedAccountService selectedAccountService;

    @GetMapping("/rekening-overzicht/details{iban}")
    public String handleDetails(@PathVariable(value = "iban") String iban,
                                Model model) {
        if (!model.containsAttribute("customerId")) {
            return "logincustomer";
        //When there is a CustomerId, check if their PaymentAccountList includes the payment account
        } else  {
            Customer customer = customerService.findCustomerBySAId(model.getAttribute("customerId"));
            for (int index = 0; index < customer.getPaymentAccounts().size(); index++) {
                if (customer.getPaymentAccounts().get(index).getIban().contains(iban)) {
                    selectedAccountService.getSelectedAccountInformation(iban, model);
                    //Iban buiten bovenstaande methode aangezien deze ook als sessionAttribute opgeslagen zal moeten worden.
                    model.addAttribute("iban", iban);
                    return "selectedaccount";
                }
            }
        }
        //When there's a customerId, but it has no access to the payment account
        return "redirect:/rekening-overzicht";
    }
}
