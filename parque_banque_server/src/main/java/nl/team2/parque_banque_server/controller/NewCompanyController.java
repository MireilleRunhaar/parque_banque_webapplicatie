package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.service.SectorService;
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
