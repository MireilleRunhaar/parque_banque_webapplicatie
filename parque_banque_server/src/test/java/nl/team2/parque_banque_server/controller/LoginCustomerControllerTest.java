package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Customer;
import nl.team2.parque_banque_server.service.CustomerService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginCustomerFormBean;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginCustomerController.class)
class LoginCustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private LoginService loginService;

    public LoginCustomerControllerTest() {
        super();
    }

    @Test
    void customerLoginFormHandler() {
        //Mockito.when(loginService.customerLoginValidation(new LoginCustomerFormBean("Jaap", "wachtwoord"))).thenReturn(true);
        Mockito.when(customerService.findByUserName("Jaap")).thenReturn(new Customer("123456789", "Jaap", "wachtwoord"));

        try {
            MockHttpServletRequestBuilder postRequest =
                    MockMvcRequestBuilders.post("/inloggen");
            postRequest.flashAttr("loginCustomerFormBean", new LoginCustomerFormBean("Jaap", "wachtwoord"));
            ResultActions result = mockMvc.perform(postRequest);
            result.andDo(print()).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
