package com.satish.mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satish.mockito.model.Employee;
import com.satish.mockito.model.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringMockitoApplicationTests {


	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("Satish Prasad");
		employee.setDept("IT");
		// convert employee object to String
		String jsonRequest = om.writeValueAsString(employee);

		// Hit the controller and get the responce in MvcResult format
		MvcResult result = mockMvc
				.perform(post("/employee/addEmployee").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		
		//it will provide the actual response object, What controller restrun the same
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isStatus() == Boolean.TRUE);
	}
	
	@Test
	public void getEmployeesTest() throws Exception {

		// Hit the controller and get the responce in MvcResult format
		MvcResult result = mockMvc
				.perform(get("/employee/getEmployees").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		
		//it will provide the actual response object, What controller restrun the same
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isStatus() == Boolean.TRUE);
	}


}
