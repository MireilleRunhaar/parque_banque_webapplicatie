package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public void saveCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public Customer findById(long id) {
        Optional<Customer> customerOption = customerRepo.findById(id);
        return customerOption.orElse(null);
    }

    public Customer findByUserName(String username) {
        return customerRepo.findByUserName(username);
    }

    //Find Customer By Session Attribute Id
    public Customer findCustomerBySAId(Object object) {
        long id = (long) object;
        return findById(id);
    }

    public CustomerRepository getCustomerRepo() {
        return customerRepo;
    }
}
