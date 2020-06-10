package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceIntegrationTest {

    private TransactionService transactionService;


    @Autowired
    public TransactionServiceIntegrationTest(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Test
    public void getPaymentAccountRepo(){
    assertNotNull(transactionService.getPaymentAccountRepo());
    }

    @Test
    public void getTransactionRepo(){
    assertNotNull(transactionService.getTransactionRepo());
    }

    @Test
    public void getPaymentAccountService(){
    assertNotNull(transactionService.getPaymentAccountService());
    }

}