package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.service.*;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("customerId")
public class ConfirmCompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private PaymentAccountService pas;
    @Autowired
    private PaymentAccountService.IbanService ibanService;
    @Autowired
    private BusinessAccountService bas;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;

    public static final long START_BALANCE = 2500L;

    public ConfirmCompanyController(){
        super();
    }

    //Klant bevestigt bedrijfsgegevens: opslaan bedrijf in DB, rekening openen, bevestigingspagina tonen
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=rekeningOpenen")
    public ModelAndView confirmNewCompanyHandler(@ModelAttribute CompanyFormBean companyFormBean, Model model) {
        ModelAndView mav = new ModelAndView("confirmnewaccount");
        Company company = companyService.createCompanyOutOfBean(companyFormBean);
        companyService.saveCompany(company);
        //make businessaccount
        BusinessAccount businessAccount =
                new BusinessAccount(ibanService.createNewIban(),
                        START_BALANCE, employeeService.findOneByRoleName("Accountmanager"),company);
        //make current customer accountholder
        businessAccount.addCustomerToAccountHolder(customerService.findCustomerBySAId(model.getAttribute("customerId")));
        //save business account
        bas.saveBusinessAccount(businessAccount);
        model.addAttribute("iban", businessAccount.getIban());
        model.addAttribute("balanceCent", pas.balanceInEuros(businessAccount.getBalance()));
        model.addAttribute("name", company.getName());
        model.addAttribute("businessAccount", true);
        return mav;
    }

    //Klant ziet fout en kan bedrijfsgegevens aanpassen
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=wijzigBedrijfsinformatie")
    public ModelAndView editNewCompanyHandler(@ModelAttribute CompanyFormBean companyFormBean) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("newcompany");
        mav.addObject("CompanyFormBean", companyFormBean);
        return mav;
    }

    //Klant bedenkt zich en keert terug naar rekeningoverzicht.
    @PostMapping(value = "/nieuw-bedrijf-aanmaken", params = "action=annulerenGaNaarRekeningoverzicht")
    public String goBackToAccountViewHandler() {
        return "accountview";
    }

}
