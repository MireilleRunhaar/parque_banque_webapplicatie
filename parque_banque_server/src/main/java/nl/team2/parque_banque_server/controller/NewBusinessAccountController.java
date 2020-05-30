package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.*;
import nl.team2.parque_banque_server.utilities.BusinessAccountBean;
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

    public static final long START_BALANCE = 2500L;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BusinessAccountService bas;
    @Autowired
    private PaymentAccountService.IbanService ibanService;
    @Autowired
    private PaymentAccountService pas;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;


    @GetMapping("/zakelijke-rekening")
    public String goToNewBusinessAccount(Model model){

        //get customer from session
        Customer customer =customerService.findCustomerBySAId(model.getAttribute("customerId"));
        if(customer != null){

            //get all the companies where customer has accounts of and add to model
            Set<Company> companies = bas.getCompaniesFromCustomer(customer);
            model.addAttribute("companies", companies);
            model.addAttribute("company", new BusinessAccountBean());
            return "newbusinessaccount";
        }else{
            return "logincustomer";
        }
    }

    @PostMapping("open-zakelijke-rekening")
    public String createBusinessAccount(@ModelAttribute("company") BusinessAccountBean bab, Model model){

        //make company
        Company company = companyService.findOneByName(bab.getKvk());

        //make businessaccount
        BusinessAccount businessAccount =
                new BusinessAccount(ibanService.createNewIban(), START_BALANCE, employeeService.findOneByRoleName("Accountmanager"),company);

        //make current customer accountholder
        businessAccount.addCustomerToAccountHolder(customerService.findCustomerBySAId(model.getAttribute("customerId")));

        //save business account
        bas.saveBusinessAccount(businessAccount);

        model.addAttribute("iban", businessAccount.getIban());
        model.addAttribute("balanceCent", pas.balanceInEuros(businessAccount.getBalance()));

        return "confirmnewaccount";
    }

}
