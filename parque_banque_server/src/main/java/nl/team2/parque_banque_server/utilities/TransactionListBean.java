package nl.team2.parque_banque_server.utilities;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionListBean {

    private long amountEuro;
    private String description;
    private LocalDate date;
    PaymentAccount creditAccount;
    PaymentAccount debitAccount;

    private final static double CENTS_TO_EURO = 100.00;

    public TransactionListBean() {
        super();
    }

    TransactionListBean transactionListBean;

    public List<TransactionListBean> convertTransactionsFromDBList(List<Transaction> transactionListFromDB) { //hoe krijg ik de transactielijst vanuit de transactieservice DB op deze plek?
        List<TransactionListBean> convertedTransactionList = new ArrayList<>();
        for (Transaction transaction : transactionListFromDB) {
            TransactionListBean tlBean = new TransactionListBean();
            tlBean.setAmountEuro(transaction.getAmountCent() / (long) CENTS_TO_EURO);
            tlBean.setDescription(transaction.getDescription());
            tlBean.setDate(transaction.getDate());
            tlBean.setCreditAccount(transaction.getCreditAccount());
            tlBean.setDebitAccount(transaction.getDebitAccount());
            convertedTransactionList.add(tlBean);
        }
        return convertedTransactionList;
    }

    public long getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(long amountEuro) {
        this.amountEuro = amountEuro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(PaymentAccount creditAccount) {
        this.creditAccount = creditAccount;
    }

    public PaymentAccount getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(PaymentAccount debitAccount) {
        this.debitAccount = debitAccount;
    }
}


