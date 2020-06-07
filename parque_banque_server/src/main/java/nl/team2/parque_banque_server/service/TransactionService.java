package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.model.repositories.TransactionRepository;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepo;
    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    PaymentAccountService paymentAccountService;


    public void executeAndSave(Transaction transaction){
        transaction.executeTransaction();
        paymentAccountRepo.save(transaction.getCreditAccount());
        paymentAccountRepo.save(transaction.getDebitAccount());
        transactionRepo.save(transaction);
    }

    public Transaction createTransactionFromBean(TransactionFormBean transactionFormBean, String ibanDebit){
            long amountCent = transactionFormBean.getTotalAmountInCents();
            PaymentAccount creditAccount = paymentAccountService.findOneByIban(transactionFormBean.getIbanCreditAccount());
            PaymentAccount debitAccount = paymentAccountService.findOneByIban(ibanDebit);
            return new Transaction(amountCent, transactionFormBean.getDescription(), LocalDate.now(), creditAccount , debitAccount);
    }

}
