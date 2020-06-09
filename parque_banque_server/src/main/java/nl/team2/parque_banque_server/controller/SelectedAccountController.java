package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Controller
@SessionAttributes({"customerId", "iban"})
public class SelectedAccountController {

    @Autowired
    CustomerService customerService;

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
                    //SAID methode maken voor PaymentAccount
                    //PaymentAccount meegeven aan model
                    //Via PaymentAccount in html kan je naam(/namen) en soort rekening ophalen
                    //Apart moeten hier de iban, het (omgerekende) saldo en de DatumEnTijd meegegeven worden
                    //En dan nog de laatste 10 transacties

                    model.addAttribute("customer", customer);
                    model.addAttribute("name", customer.getUserName()); //meer accountholders, bedrijf+accountholder, bedrijf+accountholders
                    model.addAttribute("DatumEnTijd", getCurrentTimeWithTimeZone());
                    model.addAttribute("iban", iban);
                    model.addAttribute("saldo", iban); //iban > rekening > getSaldo
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
