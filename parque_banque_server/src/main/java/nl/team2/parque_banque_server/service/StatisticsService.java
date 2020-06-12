package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import nl.team2.parque_banque_server.model.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final static long LIST_SIZE=10L;

    private final BusinessAccountRepository businessAccountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public StatisticsService(BusinessAccountRepository businessAccountRepository, CustomerRepository customerRepository) {
        this.businessAccountRepository = businessAccountRepository;
        this.customerRepository = customerRepository;
    }




    /**
     * Iterate trough the customers and business accounts and compute sum of all the balance accounts from a customer
     * @param customerList takes all the customers
     * @param businessAccountsList takes all the business accounts
     * @return a map with a key value pair of the whole name and balance
     * @author Moraad Anas
     */
    public Map<String,Long> sumBalanceBusinessAccounts(List<Customer> customerList,
                                                       List<BusinessAccount>businessAccountsList) {

        Map<String, Long> map = new HashMap<>();

        for (Customer customer : customerList) {

            long sumBalanceCent = 0L;
            for (BusinessAccount businessAccount : businessAccountsList) {

                List<Customer> customers = businessAccount.getAccountHolders();

                if (customers.contains(customer)) {
                    long balance = businessAccount.getBalanceCent();
                    sumBalanceCent = sumBalanceCent + balance;
                    String wholeName = customer.getFirstName() + " " + customer.getAffix() + " " + customer.getSurName();
                    map.put(wholeName, sumBalanceCent);
                }

            }
        }
        return map;
    }


    /**
     * sort the map, show highest value first and limit the list size to 10
     * @param unsortedMap
     * @return sortedMap
     * @author Moraad Anas
     */
    public Map<String,Long> getTop10RichestBusinessCustomersMap(Map<String,Long> unsortedMap){
        return unsortedMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(LIST_SIZE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2)->e1, LinkedHashMap::new));
    }


    /**
     * iterate trough the businessAccountList and create two parallel maps for the balance and count to compute the average balance
     * @param businessAccountsList
     * @return a map with the key value pair of the sectorName and the average balance
     * @Author Moraad Anas
     */
    public Map<String,Long> averageBalanceSector(List<BusinessAccount>businessAccountsList){
        Map<String, Long> sectorTotal = new HashMap<>(); //total sum of sector balance
        Map<String,Long>sectorCount=new HashMap<>(); //number of sector counts


        for (BusinessAccount businessAccount : businessAccountsList){
            Long balance=businessAccount.getBalanceCent();
            Company company= businessAccount.getCompany();
            Sector sector=company.getSector();
            if (sectorTotal.containsKey(sector.getName())){
                //value=(total)balance value that was already stored in the map + add the new balance
                sectorTotal.put(sector.getName(),sectorTotal.get(sector.getName())+balance);

                sectorCount.put(sector.getName(), sectorCount.get(sector.getName())+1);

                //if sectortotal map is empty(value=0) add balance & and add 1 for count
            } else{
                sectorTotal.put(sector.getName(),balance);
                sectorCount.put(sector.getName(),1L);

            }

        }
        //create new map for the average balance
        Map<String,Long> sectorAverage=new HashMap<>();

        //iterate trough the key of sectorTotal
        for(String sectorName:sectorTotal.keySet()){
            // values balance divided bij the count
            long averageBalance= sectorTotal.get(sectorName)/sectorCount.get(sectorName);
            sectorAverage.put(sectorName,averageBalance);
        }

        return sectorAverage;

    }



    public Map<String,Long> getTop10RichestBusinessCustomers(){
        Map<String, Long> map = new HashMap<>();
        List<BusinessAccount> businessAccounts= businessAccountRepository.getTenRichestBusinessBalance();
        List<Customer> customers=customerRepository.getTenRichestBusinessCustomers();
        for (BusinessAccount businessAccount:businessAccounts){
            Long balance=businessAccount.getBalanceCent();
            for (Customer customer: customers){
                String wholeName=customer.getFirstName()+customer.getAffix()+customer.getSurName();
                map.put(wholeName,balance);
            }
        }
        return map;
    }


}
