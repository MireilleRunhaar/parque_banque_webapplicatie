package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewCompanyAccountController {

    //3 scenario's mogelijk:
    //Klant is via een rekening gekoppeld aan een(of meer) bedrijf(ven)
    //Klant wil voor een van zijn/haar bedrijven een nieuwe rekening openen

    //Tonen pagina
    @GetMapping("/zakelijke-rekening-openen")
    public String showNewCompanyAccount(){
        return "zakelijke-rekening-openen";
    }

    //Klant is via een rekening gekoppeld aan een(of meer) bedrijf(ven)
    //Klant wil een nieuw bedrijf aanmaken bij Parque Banque en een rekening openen

    //Klant heeft nog geen zakelijke rekening(en)
    //Klant wil een nieuw bedrijf aanmaken bij Parque Banque en een rekening openen




}
