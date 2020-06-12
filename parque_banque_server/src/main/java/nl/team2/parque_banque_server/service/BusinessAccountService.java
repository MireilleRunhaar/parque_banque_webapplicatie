package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BusinessAccountService {

    public static final long START_BALANCE = 2500L;
    public static final String EMPLOYEE_TYPE = "Accountmanager";

    @Autowired
    private BusinessAccountRepository businessAccountRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PaymentAccountService.IbanService ibanService;
    @Autowired
    private EmployeeService employeeService;




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

    public BusinessAccount createBusinessAccountFromBean(CompanyFormBean cfb){
        Company company = companyService.findOneByKVK(cfb.getKvkNr());
        return new BusinessAccount(ibanService.createNewIban(), START_BALANCE, employeeService.findOneByRoleName(EMPLOYEE_TYPE),company);
    }

    public void saveBusinessAccount(BusinessAccount businessAccount){
        businessAccountRepo.save(businessAccount);
    }

}
