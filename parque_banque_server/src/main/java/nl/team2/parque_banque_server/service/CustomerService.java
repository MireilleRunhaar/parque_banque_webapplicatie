package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    /**
     * use this method to make a customer from the id in SessionAttributes
     * @author Lisa Kemeling
     */

    public Customer makeCustomerFromSession(Object customerId){
        Long id = (Long) customerId;
        return findById(id);
    }


    public Customer findById(Long id){
        Optional<Customer> customerOptional = customerRepo.findById(id);
        return customerOptional.orElse(null);
    }



}
