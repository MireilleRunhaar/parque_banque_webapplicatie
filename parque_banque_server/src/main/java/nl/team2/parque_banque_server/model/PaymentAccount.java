package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.List;

@Entity
public abstract class PaymentAccount {

    @Id
    private String iban;
    private long balanceCent;

    @OneToMany
    private List<Transaction> transactionHistory;

    @OneToMany
    private List<Customer> accountHolders;

        public PaymentAccount(){
        }

        public PaymentAccount(String iban, long balanceCent){
            this.iban = iban;
            this.balanceCent = balanceCent;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public long getBalance() {
            return balanceCent;
        }

        public void setBalance(long balance) {
            this.balanceCent = balanceCent;
        }

        public List<Transaction> getTransactionHistory() {
            return transactionHistory;
        }

        public void setTransactionHistory(List<Transaction> transactionHistory) {
            this.transactionHistory = transactionHistory;
        }

        public List<Customer> getAccountHolders() {
            return accountHolders;
        }

        public void setAccountHolders(List<Customer> accountHolders) {
            this.accountHolders = accountHolders;
        }

        @Override
        public String toString() {
            return "PaymentAccount{" +
                    "iban='" + iban + '\'' +
                    ", balance=" + balanceCent +
                    ", transactionHistory=" + transactionHistory +
                    ", accountHolders=" + accountHolders +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PaymentAccount that = (PaymentAccount) o;
            return balanceCent == that.balanceCent &&
                    Objects.equals(iban, that.iban) &&
                    Objects.equals(transactionHistory, that.transactionHistory) &&
                    Objects.equals(accountHolders, that.accountHolders);
        }

        @Override
        public int hashCode() {
            return Objects.hash(iban, balanceCent, transactionHistory, accountHolders);
        }
    }


