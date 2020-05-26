//package nl.team2.parque_banque_server.service;
//
//import nl.team2.parque_banque_server.model.Customer;
//import nl.team2.parque_banque_server.model.PaymentAccount;
//import nl.team2.parque_banque_server.repository.PaymentAccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PaymentAccountService {
//
//    @Autowired
//    private PaymentAccountRepository paymentAccountRepo;
//
//    public PaymentAccountService(){
//        super();
//    }
//
//    //TO DO : maak methode om een lijst te krijgen van alle accountholders op te halen behorende bij de username
//    //haal ook de iban en balanceCent op.
//
//    public PaymentAccount findPaymentAccount(List<Customer> accountHolders){
//        return paymentAccountRepo.findPaymentAccountsByAccountHolders(accountHolders);
//
//    }
//}
