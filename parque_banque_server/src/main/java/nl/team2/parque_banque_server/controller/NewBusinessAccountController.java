package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("customerId")
public class NewBusinessAccountController {

    @Autowired
    private CustomerRepository customerRepo;

    public Customer findCustomerByCustomerId(long id){
        return customerRepo.findCustomerById(id);
    }


}
