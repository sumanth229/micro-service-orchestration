package com.centime.assessment;

import com.centime.assessment.model.Name;
import com.centime.assessment.response.Response;
import com.centime.assessment.service.GreetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK, classes={ MainServiceApplication.class})
class MainServiceApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private GreetService greetService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void Greet_Name_When_ValidRequestTest() throws Exception {
        when(greetService.greet(Mockito.anyString(), Mockito.anyString())).thenReturn(new Response<>("Hello Name Test", HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/greet").content(mapToJson(new Name("Name", "Test")))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void Greet_Name_When_InvalidRequestTest() throws Exception {
        when(greetService.greet(Mockito.anyString(), Mockito.anyString())).thenReturn(new Response<>("Bad Request", HttpStatus.BAD_REQUEST));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/greet").content(mapToJson(new Name("", "Test")))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
