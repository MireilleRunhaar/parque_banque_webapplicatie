package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public void saveCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public Customer findByUserName(String username) {
        return customerRepo.findByUserName(username);
    }

    //geeft customer (value) terug die hoort bij de sessie id(key)
    public Customer findCustomerByCustomerId(long id){
        return customerRepo.findCustomerById(id);
    }
}
