package com.test.spring.boot.rest.greeting.webtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.boot.rest.greeting.service.GreetingService;
import com.test.spring.test.base.AbstractControllerTest;

@Transactional
public class GreetingControllerTest extends AbstractControllerTest {

	@Autowired
	GreetingService greetingService;

	@Before
	public void setUp() {
		super.setUp();
		greetingService.evictCache();
	}

	@Test
	public void testGetGreeting() throws Exception {
		String uri = "/api/greetings";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertNotNull("Failure: Expected not null respose", content);
		Assert.assertEquals("Failure - Expected HTT Status is {}", 200, status);
	}

}
