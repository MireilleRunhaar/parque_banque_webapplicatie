package nl.team2.parque_banque_server.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFormBeanTest {

    private TransactionFormBean transactionFormBean;

    @Test
    void getTotalAmountInCents() {
        transactionFormBean = new TransactionFormBean();
        assertEquals(10L, transactionFormBean.getTotalAmountInCents(0, 10));
        assertEquals(153L, transactionFormBean.getTotalAmountInCents(1, 53));
        assertEquals(100L, transactionFormBean.getTotalAmountInCents(1, 0));
        assertEquals(10000078L, transactionFormBean.getTotalAmountInCents(100000, 78));
        assertEquals(-1, transactionFormBean.getTotalAmountInCents(-2, 0));
        assertEquals(-1,transactionFormBean.getTotalAmountInCents(-1, -11111111));
        assertEquals(-1, transactionFormBean.getTotalAmountInCents(3, -67000));
        assertEquals(-1, transactionFormBean.getTotalAmountInCents(999999999, 34));
    }
}