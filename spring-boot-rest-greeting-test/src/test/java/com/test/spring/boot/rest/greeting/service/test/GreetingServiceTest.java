package com.test.spring.boot.rest.greeting.service.test;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.test.base.AbstractTest;

@Transactional
public class GreetingServiceTest extends AbstractTest {

	@Autowired
	com.test.spring.boot.rest.greeting.service.GreetingService greetingService;

	@Before
	public void setUp() {
		greetingService.evictCache();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testFindAll() {
		Collection<Greeting> greetings = greetingService.findAll();

		Assert.assertNotNull("Failure: Expected not null", greetings);
		Assert.assertEquals("Failure - Expected size is {}", 2, greetings.size());
	}

	@Test
	public void testFindOne() {
		Greeting greeting = greetingService.findOne(1L);

		Assert.assertNotNull("Failure: Expected not null", greeting);
	}

}
