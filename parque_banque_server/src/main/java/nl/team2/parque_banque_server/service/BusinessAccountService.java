package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepo;




    /**
     * find al the companies in the database from the user
     * @param customer takes in a customer, in this case the logged in user
     * @return the companies where this user has accounts of
     * @author Lisa Kemeling
     */
    public Set<Company> getCompaniesFromCustomer(Customer customer){
        List<BusinessAccount> businessAccounts =getBusinessAccountsByCustomer(customer);
        Set<Company> companies = new HashSet<>();
        for (BusinessAccount businessAccount :
                businessAccounts) {
            companies.add(businessAccount.getCompany());
        }
        return companies;
    }

    /** find all business accounts from user in db
     * @param customer, the loggedin user
     *return list of business accounts
     */
    public List<BusinessAccount> getBusinessAccountsByCustomer(Customer customer){
        ArrayList<Customer> accountholders = new ArrayList<>();
        accountholders.add(customer);
        return businessAccountRepo.findBusinessAccountsByAccountHoldersIn(accountholders);
    }

    public void saveBusinessAccount(BusinessAccount businessAccount){
        businessAccountRepo.save(businessAccount);
    }

    public List<BusinessAccount> findAll(){
        return businessAccountRepo.findAll();
    }




}
