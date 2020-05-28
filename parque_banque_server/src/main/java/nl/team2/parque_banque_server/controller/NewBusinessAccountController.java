package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.*;
import nl.team2.parque_banque_server.utilities.BusinessAccountBean;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author Lisa Kemeling
 * Controller for view where customer opens a new business account. Shows all known companies for this customer
 * and gives a choice for opening an account for one of these companies or a new company
 */

@Controller
@SessionAttributes("customerId")
public class NewBusinessAccountController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private NewBusinessAccountService nbas;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PaymentAccountService paymentAccountService;
    @Autowired
    private PaymentAccountService.IbanService ibanService;
    @Autowired
    private EmployeeService employeeService;



    @GetMapping("/zakelijke-rekening")
    public String goToNewBusinessAccount(Model model){

        //get customer from session
        Customer customer =customerService.makeCustomerFromSession(model.getAttribute("customerId"));
        if(customer != null){

            //get all the companies where customer has accounts of and add to model
            List<Company> companies = nbas.getCompaniesFromCustomer(customer);
            model.addAttribute("companies", companies);
            model.addAttribute("company", new BusinessAccountBean());
            return "newbusinessaccount";
        }else{
            return "logincustomer";
        }
    }

    @PostMapping(value="/open-zakelijke-rekening", params = "action:submit")
    public String createBusinessAccount(Model model){
        String name = (String) model.getAttribute("company");
        Company company = companyService.findOneByName(name);

        //make businessaccount
        BusinessAccount businessAccount =
                new BusinessAccount(ibanService.createNewIban(), 0, employeeService.findOneByRoleName("Accountmanager"),company);

        return "confirmbusinessaccount";
    }

}
