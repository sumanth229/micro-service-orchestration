package com.centime.assessment;

import com.centime.assessment.model.Name;
import com.centime.assessment.service.ConcatenationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class PostServiceApplicationTests {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private ConcatenationService concatenationService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	void contextLoads() {
	}


	@Test
	void testConcatServiceResponse_OK() {
		assert(concatenationService.concatName("Test", "Name").getResponse().equals("Test Name"));
		assert(concatenationService.concatName("Test", "Name").getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	void testConcatControllerResponse_OK() throws Exception {
		Name name = new Name("Test", "Name");
		String uri = "/api/v1/name/concat";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).content(mapToJson(name))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
	}

	@Test
	void testConcatControllerResponse_BadRequest() throws Exception {
		Name name = new Name("", "Name");
		String uri = "/api/v1/name/concat";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).content(mapToJson(name))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
}
