package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("customerId")
public class IndexController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/contact")
    public String goToContactPage() {
        List<Customer> result = customerService.returnBsnCustomers();
        System.out.println("LENGTH: " + result.size());
        for (Customer c : result) {
            System.out.println(c);
        }
        return "contact";
    }

    @GetMapping("/rekening-openen")
    public String goToNewPaymentAccount(Model model) {
        if (model.containsAttribute("customerId")) {
            return "newpaymentaccount";
        } else {
            return "logincustomer";
        }
    }
}
