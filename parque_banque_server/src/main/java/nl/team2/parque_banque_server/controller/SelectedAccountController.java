package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.service.BusinessAccountService;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"customerId", "iban"})
public class SelectedAccountController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PaymentAccountService paymentAccountService;

    @Autowired
    BusinessAccountService businessAccountService;

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
                    //Ophalen PaymentAccount
                    PaymentAccount account = paymentAccountService.findOneByIban(iban);

                    model.addAttribute("DatumEnTijd", getCurrentTimeWithTimeZone());
                    model.addAttribute("iban", iban); // slaat tevens de SessionAttribute op
                    model.addAttribute("saldo", paymentAccountService.balanceInEuros(account.getBalance()));

                    //Maakt lijst aan met namen van accountholders
                    List<Customer> accountholderList = account.getAccountHolders();
                    List<String> accountholderNamesList = new ArrayList<>();
                    for (Customer value : accountholderList) {
                        String customerName = value.getFirstName() + " " + value.getAffix() + " " + value.getSurName();
                        accountholderNamesList.add(customerName); //lijst met namen van accountholders (met loop afdrukken)
                    }
                    String output = accountholderNamesList.toString().replaceAll("(^\\[|]$)", "");

                    //If not null, it is an business account
                    if (businessAccountService.findByIban(iban) != null) {
                        BusinessAccount bAccount = businessAccountService.findByIban(iban);
                        model.addAttribute("isCompany", true);
                        model.addAttribute("businessName", bAccount.getCompany().getName());
                    }
                    model.addAttribute("names", output);
                    return "selectedaccount";
                }
            }
        }
        //When there's a customerId, but it has no access to the payment account
        return "redirect:/rekening-overzicht";
    }

    //Huidige datum en tijd weergeven
    public static String getCurrentTimeWithTimeZone(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
