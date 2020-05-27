package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.service.SectorService;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("form")
public class NewCompanyController {

    @Autowired
    private SectorService sectorService;

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
    @GetMapping("/bedrijf-aanmaken")
    public String showNewCompanyAccount(Model model) {
        Company company = new Company();
        model.addAttribute("company", new CompanyFormBean());
        model.addAttribute("sectoren", sectorService.sectorIterable());
        return "newcompany";
    }

    //Tonen van het ingevulde formulier op de confirmcompany pagina
    @PostMapping("/nieuw-bedrijf-aanmaken")
    public ModelAndView submitForm(@ModelAttribute CompanyFormBean companyFormBean) {
        ModelAndView mav = new ModelAndView("confirmcompany");
        mav.addObject("companyFormBean", companyFormBean);
        return mav;
    }

}
