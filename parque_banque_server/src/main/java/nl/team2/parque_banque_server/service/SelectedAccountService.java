package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedAccountService {

    @Autowired
    PaymentAccountService paymentAccountService;

    @Autowired
    BusinessAccountService businessAccountService;

    public void getSelectedAccountInformation(String iban, Model model) {
        PaymentAccount account = paymentAccountService.findOneByIban(iban);

        //Ophalen accountholders en check of het om een business account gaat
        String output = getListAccountHolders(account);
        businessAccountCheck(iban, model);

        model.addAttribute("DatumEnTijd", getCurrentTimeWithTimeZone());
        model.addAttribute("saldo", paymentAccountService.balanceInEuros(account.getBalance()));
        model.addAttribute("names", output);
    }

    //Makes a list with the names of the accountholders of a payment account
    private String getListAccountHolders(PaymentAccount account) {
        List<Customer> accountholderList = account.getAccountHolders();
        List<String> accountholderNamesList = new ArrayList<>();
        for (Customer value : accountholderList) {
            String customerName = value.getFirstName() + " " + value.getAffix() + " " + value.getSurName();
            accountholderNamesList.add(customerName);
        }
        return accountholderNamesList.toString().replaceAll("(^\\[|]$)", "");
    }

    //Checks if an account is a business account, if that's the case it'll also show the business name
    private void businessAccountCheck(String iban, Model model) {
        if (businessAccountService.findByIban(iban) != null) {
            BusinessAccount bAccount = businessAccountService.findByIban(iban);
            model.addAttribute("isCompany", true);
            model.addAttribute("businessName", bAccount.getCompany().getName());
        }
    }

    //Huidige datum en tijd weergeven
    public static String getCurrentTimeWithTimeZone(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
