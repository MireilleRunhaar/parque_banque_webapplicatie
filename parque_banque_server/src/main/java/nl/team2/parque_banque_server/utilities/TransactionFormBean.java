package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;

import javax.validation.constraints.*;



public class TransactionFormBean {

    public static final int MIN_AMOUNT = 0;
    public static final int MAX_AMOUNT = 100000;
    public static final int FALSE_RESULT = -1;
    public static final int MIN_CENTS = 0;
    public static final int MAX_CENTS = 10000000;

    @PositiveOrZero
    @Max(value=MAX_AMOUNT)
    private int amount;

    @PositiveOrZero
    @Max(value=MAX_CENTS)
    private int cents;

    @Size(max = 140)
    private String description;

    @NotBlank
    @Pattern(regexp = "NL\\d{2}PARQ0\\d{9}")
    private PaymentAccount creditAccount;

    public TransactionFormBean() { }

    public Transaction createTransaction(){
        long amountCent = getTotalAmountInCents(amount, cents);
       return new Transaction(amountCent, description, null, creditAccount, null);
    }

    public long getTotalAmountInCents(int amount, int cents){
        if(amount < MIN_AMOUNT || amount > MAX_AMOUNT){
            return FALSE_RESULT;
        }
        if(cents < MIN_CENTS || cents > MAX_CENTS){
            return FALSE_RESULT;
        }
        return (amount * 100) + cents;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(PaymentAccount creditAccount) {
        this.creditAccount = creditAccount;
    }
}
