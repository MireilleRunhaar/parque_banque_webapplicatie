package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.service.CompanyService;
import nl.team2.parque_banque_server.service.SectorService;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@SessionAttributes("companyFormBean")
public class NewCompanyController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/bedrijf-aanmaken")
    public String showNewCompanyPage(Model model) {
        Company company = new Company();
        model.addAttribute("companyFormBean", new CompanyFormBean());
        model.addAttribute("sectoren", sectorService.sectorIterable());
        return "newcompany";
    }

    //JS controle of kvk bekend is bij PB en eventueel teruggeven bedrijfsgegevens
    @CrossOrigin
    @PostMapping("/kvk-check")
    public @ResponseBody
    Company kvkInUseHandler(@RequestParam ("kvkNr") String kvkNr){
        Company knownCompany;
        knownCompany = companyService.findOneByKVK(kvkNr);
        return knownCompany;
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
        Sector sector = sectorService.sectorOpId(Integer.parseInt(companyFormBean.getId()));
        mav.addObject("companyFormBean", companyFormBean);
        mav.addObject("name", companyFormBean.getCompanyName());
        mav.addObject("sector", sector);
        mav.addObject("businessAccount", true);
        return mav;
    }


}
