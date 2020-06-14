package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.*;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final static long LIST_SIZE=10L;

    private final CustomerRepository customerRepository;
    private final PaymentAccountService paymentAccountService;

    @Autowired
    public StatisticsService(CustomerRepository customerRepository, PaymentAccountService paymentAccountService) {
        this.customerRepository = customerRepository;
        this.paymentAccountService = paymentAccountService;
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
//
//    /**
//     * iterate trough the businessAccountList and create two parallel maps for the balance and count to compute the average balance
//     * @param businessAccountsList
//     * @return a map with the key value pair of the sectorName and the average balance
//     * @Author Moraad Anas
//     */
//    public Map<String,Long> averageBalanceSector(List<BusinessAccount>businessAccountsList){
//        Map<String, Long> sectorTotal = new HashMap<>(); //total sum of sector balance
//        Map<String,Long>sectorCount=new HashMap<>(); //number of sector counts
//
//
//        for (BusinessAccount businessAccount : businessAccountsList){
//            Long balance=businessAccount.getBalanceCent();
//            Company company= businessAccount.getCompany();
//            Sector sector=company.getSector();
//            if (sectorTotal.containsKey(sector.getName())){
//                //value=(total)balance value that was already stored in the map + add the new balance
//                sectorTotal.put(sector.getName(),sectorTotal.get(sector.getName())+balance);
//
//                sectorCount.put(sector.getName(), sectorCount.get(sector.getName())+1);
//
//                //if sectortotal map is empty(value=0) add balance & and add 1 for count
//            } else{
//                sectorTotal.put(sector.getName(),balance);
//                sectorCount.put(sector.getName(),1L);
//
//            }
//
//        }
//        //create new map for the average balance
//        Map<String,Long> sectorAverage=new HashMap<>();
//
//        //iterate trough the key of sectorTotal
//        for(String sectorName:sectorTotal.keySet()){
//            // values balance divided bij the count
//            long averageBalance= sectorTotal.get(sectorName)/sectorCount.get(sectorName);
//            sectorAverage.put(sectorName,averageBalance);
//        }
//
//        return sectorAverage;
//
//    }



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


}
