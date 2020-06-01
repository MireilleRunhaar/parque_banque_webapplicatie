package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.model.repositories.CompanyRepository;
import nl.team2.parque_banque_server.model.repositories.EmployeeRepository;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class InitialDataService  implements ApplicationRunner {

    public static final int START_RECORDS = 10;
    @Autowired
    private BusinessAccountRepository businessAccountRepository;
    @Autowired
    PaymentAccountService.IbanService ibanService;
    @Autowired
    PaymentAccountService pas;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception{
        businessAccountRepository.saveAll(provideListOfBusinessAccounts());
    }

    public Iterable<BusinessAccount> provideListOfBusinessAccounts(){
        List<BusinessAccount> businessAccounts = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        for (int i = 0; i < START_RECORDS; i++) {
            BusinessAccount businessAccount = new BusinessAccount(ibanService.createNewIban(), pas.getRandomBalance(),
                    employeeRepository.findTopByRole_Name("Accountmanager"),companies.get(i));
            businessAccounts.add(businessAccount);
        }
        return businessAccounts;
    }

}
