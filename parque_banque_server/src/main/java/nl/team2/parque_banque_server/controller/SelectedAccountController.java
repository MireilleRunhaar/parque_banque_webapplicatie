package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Controller
@SessionAttributes("iban")
public class SelectedAccountController {

    @Autowired
    CustomerService customerService;

        @GetMapping("/rekening-overzicht/details{iban}")
        public String handleDetails(@PathVariable(value="iban") String iban,
                                    Model model) {
            if(!model.containsAttribute("customerId")) {
                return "logincustomer";
            } else {
                Customer customer = customerService.findCustomerBySAId(model.getAttribute("customerId"));
                model.addAttribute("customer", customer);
                model.addAttribute("name", customer.getUserName()); //meer accountholders, bedrijf+accountholder, bedrijf+accountholders
                model.addAttribute("DatumEnTijd", getCurrentTimeWithTimeZone());
                model.addAttribute("iban", iban);
                model.addAttribute("saldo", iban); //iban > rekening > getSaldo
                return "selectedaccount";
            }
        }

    //Van de geselecteerde rekeningnummer ophalen: rekeningnummer, tenaamstelling, saldo en de laatste 10 transacties.

    //Huidige datum en tijd weergeven
    public static String getCurrentTimeWithTimeZone(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }

    //Verwijzing naar Rekeninghouder Toevoegen
    @PostMapping(value = "/selectedaccount", params = "action=rekeninghouderToevoegen")
    public String goToAddAccountholderHandler() {
        return "redirect:/rekeninghouder-toevoegen";
    }

    //Verwijzing naar Geld overmaken
    @PostMapping(value = "/selectedaccount", params = "action=geldOvermaken")
    public String goToMakeATransactionHandler() {
        return "redirect:/overboeken";
    }

}
