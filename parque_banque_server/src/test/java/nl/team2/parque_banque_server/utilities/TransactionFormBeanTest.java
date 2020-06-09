package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFormBeanTest {

    @Test
    void getTotalAmountInCents() {
        TransactionFormBean transactionFormBean = new TransactionFormBean();
        transactionFormBean.setAmount(0);
        transactionFormBean.setCents(10);
        assertEquals(10L, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(1);
        transactionFormBean.setCents(53);
        assertEquals(153L, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(1);
        transactionFormBean.setCents(0);
        assertEquals(100L, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(100000);
        transactionFormBean.setCents(78);
        assertEquals(10000078L, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(-2);
        transactionFormBean.setCents(0);
        assertEquals(-1, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(-1);
        transactionFormBean.setCents(-11111111);
        assertEquals(-1, transactionFormBean.getTotalAmountInCents());
        transactionFormBean.setAmount(999999999);
        transactionFormBean.setCents(34);
        assertEquals(-1, transactionFormBean.getTotalAmountInCents());
    }

}