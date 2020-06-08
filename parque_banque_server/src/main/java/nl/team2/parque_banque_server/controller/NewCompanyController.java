package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.service.SectorService;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@SessionAttributes("form")
public class NewCompanyController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("/bedrijf-aanmaken")
    public String showNewCompanyPage(Model model) {
        Company company = new Company();
        model.addAttribute("newCompany", new CompanyFormBean());
        model.addAttribute("sectoren", sectorService.sectorIterable());
        return "newcompany";
    }

    //Tonen van het ingevulde formulier op de confirmcompany pagina
    @PostMapping("/nieuw-bedrijf-aanmaken")
    public ModelAndView submitNewCompanyForm(@Valid @ModelAttribute CompanyFormBean companyFormBean,
                                              BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()){
            mav.setViewName("newcompany");
            return mav;
        } else {
            mav.setViewName("confirmcompany");
        }
        mav.addObject("companyFormBean", companyFormBean);
        mav.addObject("name", companyFormBean.getName());
        mav.addObject("businessAccount", true);
        return mav;
    }

}
