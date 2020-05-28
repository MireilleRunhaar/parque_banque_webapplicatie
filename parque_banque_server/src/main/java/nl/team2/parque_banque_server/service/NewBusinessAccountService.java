package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewBusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepo;


    /**
     * find al the companies in the database from the user
     * @param customer takes in a customer, in this case the logged in user
     * @return the companies where this user has accounts of
     * @author Lisa Kemeling
     */
    public List<Company> getCompaniesFromCustomer(Customer customer){
        ArrayList<Customer> accountholders = new ArrayList<>();
        accountholders.add(customer);
        List<BusinessAccount> businessAccounts =findBusinessAccountsByCustomer(accountholders);
        List<Company> companies = new ArrayList<>();
        for (BusinessAccount businessAccount :
                businessAccounts) {
            companies.add(businessAccount.getCompany());
        }
        return companies;
    }

    //helps finding companies
    public List<BusinessAccount> findBusinessAccountsByCustomer(ArrayList<Customer> accountholders){
        return businessAccountRepo.findBusinessAccountsByAccountHoldersIn(accountholders);
    }

    /*public BusinessAccount findById(int Id){
        Optional<BusinessAccount> businessAccountOption = businessAccountRepo.findById(id); //Optional = wrapper
        if (businessAccountOption.isPresent()){
          return businessAccountOption.get();
        } else {
            return null;
        }
    }*/
}
