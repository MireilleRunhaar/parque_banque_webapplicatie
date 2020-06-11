package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.model.repositories.PaymentAccountRepository;
import nl.team2.parque_banque_server.model.repositories.TransactionRepository;
import nl.team2.parque_banque_server.utilities.AccountViewListBean;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import nl.team2.parque_banque_server.utilities.TransactionListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public static final double CENTS_IN_EURO = 100.00;

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

    public List<Transaction> getTransactionListByIbanCreditOrDebitAccount
            (String creditAccount_iban, String debitAccount_iban){
         List<Transaction> transactionListFromDB;
        transactionListFromDB = transactionRepo.findTop10ByCreditAccount_IbanOrDebitAccount_IbanOrderByDateDesc
           (creditAccount_iban, debitAccount_iban);
        return transactionListFromDB;
    }

    public List<Transaction> convertedTransactionList (List <Transaction> transactionList){
        List<Transaction> transactionListConverted = new ArrayList<>();
        for(Transaction transaction : transactionList){
            long balanceCents = transaction.getAmountCent();
            transaction.setAmountCent(paymentAccountService.balanceInEurosLong(balanceCents));
            transactionListConverted.add(transaction);
        } return transactionListConverted;
    }

    public String transactionAmountInEuros(long amountCent){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(amountCent / CENTS_IN_EURO);
    }

}
