package nl.team2.parque_banque_server;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Creates starter accounts for database and saves them. Only run firs time starting up
 * @author Lisa Kemeling
 */


@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class InitialDataService  implements ApplicationRunner {

    public static final int MAX_CUSTOMER_ID = 4000;
    public static final long MAX_BALANCE_AMOUNT = 100000000L;
    public final int INCREMENT=1;
    public final int LOWER_LIMIT=0;
    public final int UPPER_LIMIT=9;
    public static final int START_RECORDS_PA = 3000;
    public static final int START_RECORDS_BA = 1000;

    private String lastAddedPaymentAccount="NL10PARQ0100000001";

    @Autowired
    private BusinessAccountRepository businessAccountRepository;
    @Autowired
    private PrivateAccountRepository privateAccountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception{
        businessAccountRepository.saveAll(provideListOfBusinessAccounts());
        privateAccountRepository.saveAll(provideListOfPrivateAccounts());
    }

    public Iterable<PrivateAccount> provideListOfPrivateAccounts(){
        List<PrivateAccount> privateAccounts = new ArrayList<>();
        for (int i = 0; i < START_RECORDS_PA; i++) {
            String iban = createNewIban(lastAddedPaymentAccount);
            lastAddedPaymentAccount = iban;
            PrivateAccount privateAccount = new PrivateAccount(iban, getRandomBalance());
            privateAccount.addCustomerToAccountHolder(customerRepository.findCustomerById(getRandomCustomerId()));
            privateAccounts.add(privateAccount);
        }
        return privateAccounts;
    }

    public Iterable<BusinessAccount> provideListOfBusinessAccounts(){
        List<BusinessAccount> businessAccounts = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        for (int i = 0; i < START_RECORDS_BA; i++) {
            String iban = createNewIban(lastAddedPaymentAccount);
            lastAddedPaymentAccount = iban;
            BusinessAccount businessAccount = new BusinessAccount(iban, getRandomBalance(),
                    employeeRepository.findTopByRole_Name("Accountmanager"),companies.get(i));
            businessAccount.addCustomerToAccountHolder(customerRepository.findCustomerById(getRandomCustomerId()));
            businessAccounts.add(businessAccount);
        }
        return businessAccounts;
    }

    public long getRandomBalance(){
        return (long) (MAX_BALANCE_AMOUNT * Math.random()) + 1;
    }

    public long getRandomCustomerId(){
        return (long) (MAX_CUSTOMER_ID * Math.random()) + 1;
    }

    public String createNewIban(String lastAddedPaymentAccount){
        return lastAddedPaymentAccount.substring(LOWER_LIMIT,UPPER_LIMIT)
                       +(Integer.parseInt(lastAddedPaymentAccount.substring(UPPER_LIMIT))+INCREMENT);
           }

}


