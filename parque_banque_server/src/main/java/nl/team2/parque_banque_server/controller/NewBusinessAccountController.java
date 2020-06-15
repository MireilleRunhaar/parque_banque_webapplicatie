package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.*;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.Set;


/**
 * @author Lisa Kemeling
 * Controller for view where customer opens a new business account. Shows all known companies for this customer
 * and gives a choice for opening an account for one of these companies or a new company
 */

@Controller
@SessionAttributes("customerId")
public class NewBusinessAccountController {

    @Autowired private CustomerService customerService;
    @Autowired private BusinessAccountService bas;
    @Autowired private PaymentAccountService pas;
    @Autowired private SectorService sectorService;


    @GetMapping("/zakelijke-rekening")
    public String goToNewBusinessAccount(Model model){

        Customer customer =customerService.findCustomerBySAId(model.getAttribute("customerId"));
        if(customer != null){
            Set<Company> companies = bas.getCompaniesFromCustomer(customer);
            if(companies.isEmpty()){
                model.addAttribute("newCompany", new CompanyFormBean());
                model.addAttribute("sectoren", sectorService.sectorIterable());
                return "newcompany";
            } else {
            model.addAttribute("companies", companies);
            model.addAttribute("company", new CompanyFormBean());
            return "newbusinessaccount";
            }
        }else{
            return "logincustomer";
        }
    }

    @PostMapping("open-zakelijke-rekening")
    public String createBusinessAccount(@ModelAttribute("company") CompanyFormBean cfb, Model model){

        BusinessAccount businessAccount = bas.createBusinessAccountFromBean(cfb);

        businessAccount.addCustomerToAccountHolder(customerService.findCustomerBySAId(model.getAttribute("customerId")));
        bas.saveBusinessAccount(businessAccount);

        model.addAttribute("iban", businessAccount.getIban());
        model.addAttribute("balanceCent", pas.balanceInEuros(businessAccount.getBalance()));
        model.addAttribute("businessAccount", true);
        model.addAttribute("name", businessAccount.getCompany().getName());

        return "confirmnewaccount";
    }

}
