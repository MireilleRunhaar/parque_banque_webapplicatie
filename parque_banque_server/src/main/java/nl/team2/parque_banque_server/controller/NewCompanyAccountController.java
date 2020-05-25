package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class NewCompanyAccountController {

    //Klant is via een rekening gekoppeld aan een(of meer) bedrijf(ven)
    //Klant wil voor een van zijn/haar bedrijven een nieuwe rekening openen
    //Tonen pagina
    @GetMapping("/zakelijke-rekening-openen")
    public String showNewCompanyAccount(){
        return "zakelijke-rekening-openen";
    }
    //Uit de DB de bedrijven te halen van de ingelogde klant

    //Klant is via een rekening gekoppeld aan een(of meer) bedrijf(ven)
    //Klant wil een nieuw bedrijf aanmaken bij Parque Banque en een rekening openen

    //Klant heeft nog geen zakelijke rekening(en)
    //Klant wil een nieuw bedrijf aanmaken bij Parque Banque en een rekening openen
    //Tonen van de pagina newcompanyaccount met invulformulier
    @GetMapping("/newcompanyaccount")
    public String showNewCompanyAccount(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        //List<String> sectoren = Arrays.asList("Handel", "Industrie", "Zorg");
        //model.addAttribute("sector", sectoren);
        return "newcompanyaccount";
    }
    //Tonen van het ingevulde formulier op de confirmcompany pagina
    @PostMapping("/newcompanyaccount")
    public String submitForm(@ModelAttribute("company") Company company) {
        System.out.println(company);
        return "confirmcompany";
    }



}
