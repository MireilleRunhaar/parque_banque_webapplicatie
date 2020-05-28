package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.NewBusinessAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

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

    @GetMapping("/zakelijke-rekening")
    public String goToNewBusinessAccount(Model model){

        //get customer from session
        Customer customer =customerService.makeCustomerFromSession(model.getAttribute("customerId"));
        if(customer != null){

            //get all the companies where customer has accounts of and add to model
            List<Company> companies = nbas.getCompaniesFromCustomer(customer);
            model.addAttribute("companies", companies);
            return "newbusinessaccount";
        }else{
            return "logincustomer";
        }
    }
}
