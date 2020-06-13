package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.model.repositories.TransactionRepository;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private PaymentAccountRepository paymentAccountRepo;
    private TransactionRepository transactionRepo;
    private PaymentAccountService paymentAccountService;

    @Autowired
    public TransactionService(PaymentAccountRepository paymentAccountRepo, TransactionRepository transactionRepo, PaymentAccountService paymentAccountService) {
        this.paymentAccountRepo = paymentAccountRepo;
        this.transactionRepo = transactionRepo;
        this.paymentAccountService = paymentAccountService;
    }

    public void executeAndSave(Transaction transaction){
        transaction.executeTransaction();
        paymentAccountRepo.save(transaction.getDebitAccount());
        paymentAccountRepo.save(transaction.getCreditAccount());
        transactionRepo.save(transaction);
    }

    public Transaction createTransactionFromBean(TransactionFormBean transactionFormBean, String ibanDebit){
            long amountCent = transactionFormBean.getTotalAmountInCents();
            PaymentAccount creditAccount = paymentAccountService.findOneByIban(transactionFormBean.getIbanCreditAccount());
            PaymentAccount debitAccount = paymentAccountService.findOneByIban(ibanDebit);
            return new Transaction(amountCent, transactionFormBean.getDescription(), LocalDate.now(), creditAccount , debitAccount);
    }

    public List<Transaction> getTransactionListByIbanCreditOrDebitAccount(String creditAccount_iban, String debitAccount_iban){
        return transactionRepo.findTop10ByCreditAccount_IbanOrDebitAccount_IbanOrderByDateDesc
                (creditAccount_iban, debitAccount_iban);
    }

    public PaymentAccountRepository getPaymentAccountRepo() {
        return paymentAccountRepo;
    }


    public TransactionRepository getTransactionRepo() {
        return transactionRepo;
    }


    public PaymentAccountService getPaymentAccountService() {
        return paymentAccountService;
    }

}
