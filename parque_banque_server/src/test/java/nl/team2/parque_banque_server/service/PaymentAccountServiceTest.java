package nl.team2.parque_banque_server.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentAccountServiceTest {


    // FIXME: 30/05/2020 Waarom slaagt de test niet terwijl output hetzelfde is? Misschien ziet een ander het?
    @Test
    void balanceInEuros(){
        PaymentAccountService paymentAccountService= new PaymentAccountService();
       assertEquals("€ 25,00", paymentAccountService.balanceInEuros(2500));
       assertEquals("€ 0,45", paymentAccountService.balanceInEuros(45));
       assertEquals("€ 1,32", paymentAccountService.balanceInEuros(132));
       assertEquals("€ 1.111,11",paymentAccountService.balanceInEuros(111111L));
       assertEquals("€ 99.969,34", paymentAccountService.balanceInEuros(9996934L));
       assertEquals("€ 345.126,67", paymentAccountService.balanceInEuros(34512667L));
       assertEquals("€ 1.234.562.123.873,00", paymentAccountService.balanceInEuros(123456212387300L));
    }
}
