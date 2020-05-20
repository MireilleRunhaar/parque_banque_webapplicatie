package nl.team2.parque_banque_server.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Transaction {

    private int amountCent;//
    private String description;
    private LocalDate date;
    private PaymentAccount creditAccount;
    private PaymentAccount debitAccount;

    public Transaction(){
            }

    public Transaction(int amountCent, String description, LocalDate date, PaymentAccount creditAccount, PaymentAccount debitAccount) {
        this.amountCent=amountCent;
        this.description=description;
        this.date = date;
        this.creditAccount=creditAccount;
        this.debitAccount = debitAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountCent() {
        return amountCent;
    }

    public void setAmountCent(int amountCent) {
        this.amountCent = amountCent;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "amountCent=" + amountCent +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amountCent == that.amountCent &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(creditAccount, that.creditAccount) &&
                Objects.equals(debitAccount, that.debitAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountCent, description, date, creditAccount, debitAccount);
    }
}
