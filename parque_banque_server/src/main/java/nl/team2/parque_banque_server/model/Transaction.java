package nl.team2.parque_banque_server.model;

import java.util.Date;

public class Transaction {

    private double amount;
    private String description;
    private Date date;
    private PaymentAccount creditAccount;
    private PaymentAccount debitAccount;

    public Transaction(){

    }

    public Transaction(double amount, String description, Date date, PaymentAccount creditAccount, PaymentAccount debitAccount) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                '}';
    }
}
