package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.PrivateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateAccountService {

    @Autowired
    private PrivateAccountRepository privateAccountRepository;

    public List<PrivateAccount> getPrivateAccountsByCustomer(Customer customer){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        return privateAccountRepository.findPrivateAccountsByAccountHoldersIn(customerList);
    }

    public void savePrivateAccount(PrivateAccount privateAccount){
        privateAccountRepository.save(privateAccount);
    }

//    public PrivateAccount findIdByIban(String iban){
//        return privateAccountRepository.findIdByIban(iban);
//    }


}
