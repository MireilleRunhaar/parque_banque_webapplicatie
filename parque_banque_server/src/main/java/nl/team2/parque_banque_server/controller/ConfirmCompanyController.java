package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.service.CompanyService;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId")
public class ConfirmCompanyController {

    @Autowired
    private CompanyService companyService;

    public ConfirmCompanyController(){
        super();
    }

    //Klant bevestigt bedrijfsgegevens: opslaan bedrijf in DB, rekening openen, bevestigingspagina tonen
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=rekeningOpenen")
    public ModelAndView confirmNewCompanyHandler(@ModelAttribute CompanyFormBean companyFormBean) {
        ModelAndView mav = new ModelAndView("newbusinessaccount");
        Company company = companyService.createCompanyOutOfBean(companyFormBean);
        companyService.saveCompany(company);
        return mav;
    }

    //Klant ziet fout en kan bedrijfsgegevens aanpassen
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=wijzigBedrijfsinformatie")
    public ModelAndView editNewCompanyHandler(@ModelAttribute CompanyFormBean companyFormBean) {
        ModelAndView mav = new ModelAndView("newbusinessaccount");
        Company company = companyService.createCompanyOutOfBean(companyFormBean);
        return mav;
    }

    //Klant bedenkt zich en keert terug naar rekeningoverzicht.
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=annulerenGaNaarRekeningoverzicht")
    public String goBackToAccountViewHandler() {
        return "accountview";
    }

}
