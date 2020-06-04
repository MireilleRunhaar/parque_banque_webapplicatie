package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;


public class TransactionFormBean {

    private int amount;
    private int cents;
    private String description;
    private PaymentAccount creditAccount;

    public TransactionFormBean() { }

    // TODO: 04/06/2020 transactie maken
    public Transaction createTransaction(){
        Transaction transaction = null;
        return transaction;
    }



}
