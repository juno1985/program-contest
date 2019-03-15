package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.contest.ProgramContestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProgramContestApplication.class)
public class ProgramContestApplicationTests {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setupMockMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getProblem() throws Exception {

		String url = "/challenge/1/problem";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int statusCode = response.getStatus();

		String resultString = response.getContentAsString();

		System.out.println("Status Code: " + statusCode);

		// System.out.println(resultString);
	}

	@Test
	public void testResponseBuffer() throws Exception {

		String url = "/test/response/buffer";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int statusCode = response.getStatus();

		String resultString = response.getContentAsString();

		System.out.println("Status Code: " + statusCode);

		System.out.println(resultString);
	}

	@Test
	public void testMultiRequestThread() {
		for (int i = 0; i < 10; i++) {
			Thread thread = new MultiRequestThread("thread-" + i);
			thread.start();

		}
	}

	class MultiRequestThread extends Thread {

		public MultiRequestThread(String name) {
			setName(name);
		}

		@Override
		public void run() {

			String url = "/test/response/buffer";

			try {
				MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
						.andReturn();
				MockHttpServletResponse response = mvcResult.getResponse();

				int statusCode = response.getStatus();

				String resultString = response.getContentAsString();

				System.out.println("Status Code: " + statusCode);

				System.out.println(resultString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Test
	public void registUser() throws Exception {

		String url = "/user/regist";

		String requestJSON = "{\"username\":\"wan\",\"password\":\"\",\"phone\":\"13888888888\",\"email\":\"wang@126.com\"}";

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
						.post(url)
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.code").value(1))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		int statusCode = response.getStatus();

		String resultString = response.getContentAsString();

		System.out.println("Status Code: " + statusCode);

		System.out.println("Result: " + resultString);
	}

}
