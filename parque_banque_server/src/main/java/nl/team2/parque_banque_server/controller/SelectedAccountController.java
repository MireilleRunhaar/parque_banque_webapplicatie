package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Controller
//Todo: Add SessionAttributes: IBAN NR
public class SelectedAccountController {

    @GetMapping("/selectedaccount")
    public String showSelectedAccountPageHandler(){
        return "selectedaccount";
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

    //Verwijzing naar Geld overmaken
}
