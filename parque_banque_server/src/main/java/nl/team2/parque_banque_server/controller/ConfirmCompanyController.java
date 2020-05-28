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
@SessionAttributes("company")
public class ConfirmCompanyController {

    @Autowired
    private CompanyService companyService;

    public ConfirmCompanyController(){
        super();
    }

    //Als klant ingevulde bedrijfsgegevens goedkeurt volgen 2 acties: bedrijf in DB en zakelijke rekening aanmaken
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=rekeningOpenen")
    public ModelAndView confirmNewCompanyHandler(@ModelAttribute CompanyFormBean companyFormBean) {
        ModelAndView mav = new ModelAndView("newbusinessaccount");
        Company company = companyService.createCompanyOutOfBean(companyFormBean);
        companyService.saveCompany(company);
        return mav;
    }

    //Als klant zich bedenkt moet hij/zij terugkeren naar het rekeningoverzicht.
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=annulerenGaNaarRekeningoverzicht")
    public String goBackToAccountViewHandler() {
        return "accountview";
    }

}
