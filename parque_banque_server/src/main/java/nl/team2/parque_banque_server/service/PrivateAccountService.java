package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.PrivateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PrivateAccountService {

    @Autowired
    private PrivateAccountRepository privateAccountRepository;

    /**find all private accounts from user in database
     * @param customer, the logged in user
     * @return list of privateaccounts
     */

    public List<PrivateAccount> getPrivateAccountsByCustomer(Customer customer){
        ArrayList<Customer> accountholders = new ArrayList<>();
        accountholders.add(customer);
        List<PrivateAccount> privateAccounts = findPrivateAccountsByCustomer(accountholders);
        return privateAccounts;
    }

    public List <PrivateAccount> findPrivateAccountsByCustomer(ArrayList<Customer> accountholders){
        return privateAccountRepository.findPrivateAccountsByAccountHoldersIn(accountholders);
    }

    public void savePrivateAccount(PrivateAccount privateAccount){
        privateAccountRepository.save(privateAccount);
    }

  }
