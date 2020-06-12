package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StatisticsService {

    private final CustomerRepository customerRepository;
    private final PaymentAccountService paymentAccountService;

    @Autowired
    public StatisticsService(CustomerRepository customerRepository, PaymentAccountService paymentAccountService) {
        this.customerRepository = customerRepository;
        this.paymentAccountService = paymentAccountService;
    }

    private List<Customer> getTenRichestPrivateCustomers() {
        return customerRepository.getTenRichestPrivateCustomers();
    }

    public Map<String, Object[]> getTenRichestPrivateCustomersStatistics() {
        List<Customer> customers = getTenRichestPrivateCustomers();
        Map<String, Object[]> results = new TreeMap<>(Collections.reverseOrder());
        long totalBalance = 0L;
        int numberOfAccounts = 0;
        for (Customer c : customers) {
            for (PaymentAccount paymentAccount : c.getPaymentAccounts()) {
                if (paymentAccount instanceof PrivateAccount) {
                    totalBalance += paymentAccount.getBalance();
                    numberOfAccounts += 1;
                }
            }
            String formattedBalance = paymentAccountService.balanceInEuros(totalBalance);
            results.put(formattedBalance, new Object[]{c, numberOfAccounts});
            totalBalance = 0;
            numberOfAccounts = 0;
        }
        return results;
    }

}
