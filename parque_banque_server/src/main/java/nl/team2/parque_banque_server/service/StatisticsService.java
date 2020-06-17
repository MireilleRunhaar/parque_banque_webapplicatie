package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import nl.team2.parque_banque_server.model.*;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;

import java.util.*;

@Service
public class StatisticsService {


    //private final static long LIST_SIZE=10L;

    private final CustomerRepository customerRepository;
    private final PaymentAccountService paymentAccountService;

    private final BusinessAccountRepository businessAccountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public StatisticsService(CustomerRepository customerRepository,
                             PaymentAccountService paymentAccountService,
                             BusinessAccountRepository businessAccountRepository,
                             TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.paymentAccountService = paymentAccountService;
        this.businessAccountRepository = businessAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    private List<Customer> getTenRichestPrivateCustomers() {
        return customerRepository.getTenRichestPrivateCustomers();
    }

    public Map<Long, Object[]> getTenRichestPrivateCustomersStatistics() {
        List<Customer> customers = getTenRichestPrivateCustomers();
        Map<Long, Object[]> results = new TreeMap<>(Collections.reverseOrder());
        long totalBalance = 0L;
        int numberOfAccounts = 0;
        for (Customer c : customers) {
            for (PaymentAccount paymentAccount : c.getPaymentAccounts()) {
                if (paymentAccount instanceof PrivateAccount) {
                    totalBalance += paymentAccount.getBalance();
                    numberOfAccounts += 1;
                }
            }
            results.put(totalBalance, new Object[]{c, numberOfAccounts});
            totalBalance = 0;
            numberOfAccounts = 0;
        }
        return results;
    }


    public Map<Integer,Object[]> getTenRichestBusinessCustomers(){
        Map<Integer, Object[]> map = new TreeMap<>();
        List<Customer> customers=customerRepository.getTenRichestBusinessCustomers();
        long totalBalance=0L;
        int numberOfAccounts=0;
        int count=0;
        for (Customer customer:customers){
           for (PaymentAccount paymentAccount: customer.getPaymentAccounts()){
               if (paymentAccount instanceof BusinessAccount){
                   totalBalance+=paymentAccount.getBalance();
                   numberOfAccounts+=1;
               }
           }
           count+=1;
           map.put(count,new Object[]{customer,totalBalance,numberOfAccounts});
           totalBalance=0;
           numberOfAccounts=0;

        }
        return map;
    }


    /**
     * iterate trough the businessAccountList and create two parallel maps for the balance and count to compute the average balance
     * @param businessAccountsList
     * @return a map with the key value pair of the sectorName and the average balance
     * @Author Moraad Anas
     */
    public Map<Long,Object> averageBalanceSector(Iterable<BusinessAccount>businessAccountsList){
        Map<Object, Long[]> sectorSumAndCount = new HashMap<>(); //total sum balance and number of sector

        for (BusinessAccount businessAccount : businessAccountsList){
            Long balance=businessAccount.getBalanceCent();
            Sector sector=businessAccount.getCompany().getSector();
            if (sectorSumAndCount.containsKey(sector)){
                //value=(total)balance+count that was already stored in the map; add new balance and count to it
                sectorSumAndCount.put(sector,new Long[]{sectorSumAndCount.get(sector)[0]+balance,
                        sectorSumAndCount.get(sector)[1]+1});

                //if sectortotal map is empty, add balance & and add 1 for count (map starts with this Else statement)
            } else{
                sectorSumAndCount.put(sector,new Long[]{balance,1L});
            }

        }
        //create new map for the average balance
        Map<Long,Object> sectorAverage=new TreeMap<>(Collections.reverseOrder());

        //iterate trough the key of sectorSumAndCount
        for(Object sector:sectorSumAndCount.keySet()){
            // values balance divided bij the count

            long averageBalance= ((sectorSumAndCount.get(sector)[0])* (long) 1.0)/ (sectorSumAndCount.get(sector)[1]);
            sectorAverage.put(averageBalance,sector);
        }

        return sectorAverage;

    }

        public Map<Integer,Object[]> getTenMostActiveBusinessCustomers(){
        Map<Integer, Object[]> map = new TreeMap<>();
        List<Customer> customers=customerRepository.getTenMostActiveCustomers();
        long totalTransactions=0;
        int count=0;
        for (Customer customer:customers){

            for (PaymentAccount paymentAccount:customer.getPaymentAccounts()){
                if(paymentAccount instanceof BusinessAccount){

                    List<Transaction> transactions=transactionRepository.findAll();

                    for (Transaction transaction:transactions){

                        if (transaction.getDebitAccount().equals(paymentAccount)||
                                transaction.getCreditAccount().equals(paymentAccount)){
                                totalTransactions+=1L;
                        }

                    }
                }
            }

            count+=1;
            map.put(count,new Object[]{customer,totalTransactions});
            totalTransactions=0;



        }
        return map;
    }

}
