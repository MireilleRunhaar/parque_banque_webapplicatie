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
import nl.team2.parque_banque_server.model.*;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import nl.team2.parque_banque_server.model.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {


    //private final static long LIST_SIZE=10L;

    private final CustomerRepository customerRepository;
    private final PaymentAccountService paymentAccountService;

    private final BusinessAccountRepository businessAccountRepository;

    @Autowired
    public StatisticsService(CustomerRepository customerRepository,
                             PaymentAccountService paymentAccountService,
                             BusinessAccountRepository businessAccountRepository) {
        this.customerRepository = customerRepository;
        this.paymentAccountService = paymentAccountService;
        this.businessAccountRepository = businessAccountRepository;
    }

    



    public Map<Long,Object[]> getTenRichestBusinessCustomers(){
        Map<Long, Object[]> map = new TreeMap<>(Collections.reverseOrder());
        List<Customer> customers=customerRepository.getTenRichestBusinessCustomers();
        long totalBalance=0L;
        int numberOfAccounts=0;
        for (Customer customer:customers){
           for (PaymentAccount paymentAccount: customer.getPaymentAccounts()){
               if (paymentAccount instanceof BusinessAccount){
                   totalBalance+=paymentAccount.getBalance();
                   numberOfAccounts+=1;
               }
           }
           map.put(totalBalance,new Object[]{customer,numberOfAccounts});
           totalBalance=0;
           numberOfAccounts=0;

        }
        return map;
    }

//    public Map<Long,Object[]> getAverageBalanceSector(){
//        Map<Long,Object[]>map=new TreeMap<>();
//        List<BusinessAccount>businessAccounts=businessAccountRepository.findAll();
//        long averageBalance=averageBalanceSector();
//        for(BusinessAccount businessAccount:businessAccounts){
//            averageBalance+=businessAccount.getBalance();
//            map.put(averageBalance,new Object[]{businessAccount.getCompany().getSector()});
//            averageBalance=0;
//        }
//        return map;
//    }

    /**
     * iterate trough the businessAccountList and create two parallel maps for the balance and count to compute the average balance
     * @param businessAccountsList
     * @return a map with the key value pair of the sectorName and the average balance
     * @Author Moraad Anas
     */
    public Map<Long,Object[]> averageBalanceSector(Iterable<BusinessAccount>businessAccountsList){
        Map<Object, Long> sectorTotal = new HashMap<>(); //total sum of sector balance
        Map<Object,Long>sectorCount=new HashMap<>(); //number of sector counts


        for (BusinessAccount businessAccount : businessAccountsList){
            Long balance=businessAccount.getBalanceCent();
            Sector sector=businessAccount.getCompany().getSector();
            if (sectorTotal.containsKey(sector)){
                //value=(total)balance value that was already stored in the map + add the new balance
                sectorTotal.put(sector,sectorTotal.get(sector)+balance);

                sectorCount.put(sector, sectorCount.get(sector)+1);

                //if sectortotal map is empty(value=0) add balance & and add 1 for count
            } else{
                sectorTotal.put(sector,balance);
                sectorCount.put(sector,1L);

            }

        }
        //create new map for the average balance
        Map<Long,Object[]> sectorAverage=new TreeMap<>(Collections.reverseOrder());

        //iterate trough the key of sectorTotal
        for(Object sector:sectorTotal.keySet()){
            // values balance divided bij the count
            long averageBalance= sectorTotal.get(sector)/sectorCount.get(sector);
            sectorAverage.put(averageBalance,new Object[]{sector});
        }

        return sectorAverage;

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

//    public Map<Long,Object[]> getTenMostActiveCustomers(){
//        Map<Long, Object[]> map = new TreeMap<>(Collections.reverseOrder());
//        List<Customer> customers=customerRepository.getTenMostActiveCustomers();
//        long totalTransactions=0L;
//        for (Customer customer:customers){
//            for (PaymentAccount paymentAccount:customer.getPaymentAccounts()){
//                map.put(paymentAccount.get)
//            }
//        }
//
//    }











}
