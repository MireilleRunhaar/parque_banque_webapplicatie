package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.PaymentAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.model.Transaction;
import nl.team2.parque_banque_server.service.PaymentAccountService;
import nl.team2.parque_banque_server.service.TransactionService;
import nl.team2.parque_banque_server.utilities.TransactionFormBean;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewTransactionController.class)
class NewTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentAccountService paymentAccountService;
    @MockBean
    private TransactionService transactionService;

    public NewTransactionControllerTest() {
    }

    @Test
    void transactionHandler() {
        PaymentAccount credit = new PrivateAccount("NL80PARQ0100000841", 2500);
        PaymentAccount debit = new PrivateAccount("NL80PARQ0100000842", 2500);
        Mockito.when(paymentAccountService.findOneByIban("NL80PARQ0100000841"))
                .thenReturn(credit);
        Mockito.when(paymentAccountService.validateFunds("NL80PARQ0100000842", 1000))
                .thenReturn(true);
        Mockito.when(transactionService.createTransactionFromBean(new TransactionFormBean(), "NL80PARQ0100000842"))
                .thenReturn(new Transaction(1000, "String description", LocalDate.now(), credit, debit));


        try{
           MockHttpServletRequestBuilder postRequest =
                    MockMvcRequestBuilders.post("/overboeken");
           postRequest.flashAttr("transactionFormBean", new TransactionFormBean());
            ResultActions result = mockMvc.perform(postRequest);
            result.andDo(print()).andExpect(status().isOk());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}