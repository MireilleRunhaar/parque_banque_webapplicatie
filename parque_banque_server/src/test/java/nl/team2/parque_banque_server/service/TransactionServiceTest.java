package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.model.repositories.TransactionRepository;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class TransactionServiceTest {

    @MockBean
    PaymentAccountService paymentAccountService = Mockito.mock(PaymentAccountService.class);
    @MockBean
    PaymentAccountRepository paymentAccountRepo =Mockito.mock(PaymentAccountRepository.class);
    @MockBean
    TransactionRepository transactionRepo= Mockito.mock(TransactionRepository.class);

    TransactionService transactionService = new TransactionService(paymentAccountRepo, transactionRepo, paymentAccountService);
    PaymentAccount creditAccount = new PrivateAccount("NL80PARQ0100000841", 2500);
    PaymentAccount debitAccount = new PrivateAccount("NL80PARQ0100000500", 2500);
    String ibanCredit = "NL80PARQ0100000841";
    String ibanDebit = "NL80PARQ0100000500";

    @BeforeEach
    public void setup(){
        Mockito.when(paymentAccountService.findOneByIban(ibanCredit)).thenReturn(creditAccount);
        Mockito.when(paymentAccountService.findOneByIban(ibanDebit)).thenReturn(debitAccount);
    }

    @Test
    void createTransactionFromBean() {
        assertEquals(new Transaction(100, "test1", LocalDate.now(), creditAccount, debitAccount),
                transactionService.createTransactionFromBean(new TransactionFormBean(1, 0, "test1", ibanCredit),ibanDebit));

    }

    @Test
    void createTransactionFromBean2(){
        assertEquals(new Transaction(23456, "test1456", LocalDate.now(), creditAccount, debitAccount),
                transactionService.createTransactionFromBean(new TransactionFormBean(234, 56, "test11456", ibanCredit),ibanDebit));
    }

    // TODO: 07/06/2020 twee testen waarin iets fout kan gaan

}