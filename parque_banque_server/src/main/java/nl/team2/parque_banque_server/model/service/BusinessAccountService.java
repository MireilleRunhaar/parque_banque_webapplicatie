package nl.team2.parque_banque_server.model.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.repositories.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessAccountService {

    @Autowired
    private BusinessAccountRepository businessAccountRepo;

    /*public BusinessAccount findById(int Id){
        Optional<BusinessAccount> businessAccountOption = businessAccountRepo.findById(id); //Optional = wrapper
        if (businessAccountOption.isPresent()){
          return businessAccountOption.get();
        } else {
            return null;
        }
    }*/
}
