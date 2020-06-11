package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.model.repositories.TransactionRepository;
import nl.team2.parque_banque_server.utilities.SelectedAccountViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedAccountService {

    @Autowired
    PaymentAccountService paymentAccountService;

    @Autowired
    BusinessAccountService businessAccountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepo;

    public void getSelectedAccountInformation(String iban, Model model) {
        PaymentAccount account = paymentAccountService.findOneByIban(iban);

        //Gets a String with the accountholder(s) and checks if the accoutn is a business account
        String output = getListAccountHolders(account);
        businessAccountCheck(iban, model);

        model.addAttribute("DatumEnTijd", getCurrentTimeWithTimeZone());
        model.addAttribute("saldo", paymentAccountService.balanceInEuros(account.getBalance()));
        model.addAttribute("names", output);

        //Gets a list with the 10 most recent transactions, with the amount in euro's
        List<SelectedAccountViewBean> transactionListEuro = getTransactionListEuros(iban);
        model.addAttribute("transacties", transactionListEuro);
    }


    //Creates a list with the names of the accountholders of a payment account
    private String getListAccountHolders(PaymentAccount account) {
        List<Customer> accountholderList = account.getAccountHolders();
        List<String> accountholderNamesList = new ArrayList<>();
        for (Customer value : accountholderList) {
            String customerName = value.getFirstName() + " " + value.getAffix() + " " + value.getSurName();
            accountholderNamesList.add(customerName);
        }
        return accountholderNamesList.toString().replaceAll("(^\\[|]$)", "");
    }

    //Checks if an account is a business account, if that's the case it'll also show the business name
    private void businessAccountCheck(String iban, Model model) {
        if (businessAccountService.findByIban(iban) != null) {
            BusinessAccount bAccount = businessAccountService.findByIban(iban);
            model.addAttribute("isCompany", true);
            model.addAttribute("businessName", bAccount.getCompany().getName());
        }
    }

    //Gets a list with Transactions and converts the
    private List<SelectedAccountViewBean> getTransactionListEuros(String iban) {
        List<Transaction> transactionList = transactionService.getTransactionListByIbanCreditOrDebitAccount(iban, iban);
        return convertedTransactionList(transactionList, iban);
    }

    //Converts the amount in cents to amount in euros and adds information to a bean to be able to show the information
    // later on in the HTML page.
    private List<SelectedAccountViewBean> convertedTransactionList (List <Transaction> transactionList, String iban){
        List<SelectedAccountViewBean> transactionListConverted = new ArrayList<>();
        for(Transaction transaction : transactionList){
            SelectedAccountViewBean bean = new SelectedAccountViewBean();
            String amount = getDebitOrCredit(iban, transaction, bean);
            bean.setAmount(amount);
            bean.setDescription(transaction.getDescription());
            bean.setDate(transaction.getDate());
            transactionListConverted.add(bean);
        } return transactionListConverted;
    }

    //Sets the iban of the opposite payment account and gets the amount in euro's
    private String getDebitOrCredit(String iban, Transaction transaction, SelectedAccountViewBean bean) {
        String amount;
        if (transaction.getCreditAccount().getIban().equals(iban)) {
            bean.setOppositeAccountIban(transaction.getDebitAccount().getIban());
            amount = "+ " + paymentAccountService.balanceInEuros(transaction.getAmountCent());
        } else {
            bean.setOppositeAccountIban(transaction.getCreditAccount().getIban());
            amount = "- " + paymentAccountService.balanceInEuros(transaction.getAmountCent());
        }
        return amount;
    }

    public static String getCurrentTimeWithTimeZone(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
