package nl.team2.parque_banque_server.service;

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




//
//    /**
//     * Iterate trough the customers and business accounts and compute sum of all the balance accounts from a customer
//     * @param customerList takes all the customers
//     * @param businessAccountsList takes all the business accounts
//     * @return a map with a key value pair of the whole name and balance
//     * @author Moraad Anas
//     */
//    public Map<String,Long> sumBalanceBusinessAccounts(List<Customer> customerList,
//                                                       List<BusinessAccount>businessAccountsList) {
//
//        Map<String, Long> map = new HashMap<>();
//
//        for (Customer customer : customerList) {
//
//            long sumBalanceCent = 0L;
//            for (BusinessAccount businessAccount : businessAccountsList) {
//
//                List<Customer> customers = businessAccount.getAccountHolders();
//
//                if (customers.contains(customer)) {
//                    long balance = businessAccount.getBalanceCent();
//                    sumBalanceCent = sumBalanceCent + balance;
//                    String wholeName = customer.getFirstName() + " " + customer.getAffix() + " " + customer.getSurName();
//                    map.put(wholeName, sumBalanceCent);
//                }
//
//            }
//        }
//        return map;
//    }
//
//
//    /**
//     * sort the map, show highest value first and limit the list size to 10
//     * @param unsortedMap
//     * @return sortedMap
//     * @author Moraad Anas
//     */
//    public Map<String,Long> getTop10RichestBusinessCustomersMap(Map<String,Long> unsortedMap){
//        return unsortedMap.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(LIST_SIZE)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2)->e1, LinkedHashMap::new));
//    }
//





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
    public Map<Long,Object[]> averageBalanceSector(List<BusinessAccount>businessAccountsList){
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











}
