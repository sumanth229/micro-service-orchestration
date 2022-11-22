package com.centime.assessment;

import com.centime.assessment.controller.GetHelloController;
import com.centime.assessment.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class GetServiceApplicationTests {

	@Autowired
	private HelloService helloService;

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private GetHelloController getHelloController;

	@Test
	void contextLoads(){

	}

	@Test
	void testHelloServiceResponse() {
		assert(helloService.getHello().getResponseObject().equals("Hello"));
	}

	@Test
	void testHelloControllerResponse() throws Exception {
		String uri = "/api/v1/hello";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertEquals("Hello", mvcResult.getResponse().getContentAsString());
	}
}
