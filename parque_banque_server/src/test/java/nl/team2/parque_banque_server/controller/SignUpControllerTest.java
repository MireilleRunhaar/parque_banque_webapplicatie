package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.service.SignUpService;
import nl.team2.parque_banque_server.utilities.SignUpFormBean;
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

@WebMvcTest(SignUpController.class)
public class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignUpService signUpService;

    @Test
    void signUpTest() {
        Mockito.when(signUpService.passesElfproef("123456782")).thenReturn(true);

        SignUpFormBean form = new SignUpFormBean();
        form.setBsn("123456782");

        try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/klant-worden");
            postRequest.flashAttr("signupform", form);
            ResultActions result = mockMvc.perform(postRequest);
            result.andDo(print()).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
