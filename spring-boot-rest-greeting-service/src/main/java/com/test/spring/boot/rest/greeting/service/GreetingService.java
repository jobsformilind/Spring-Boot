package com.test.spring.boot.rest.greeting.service;

import java.util.Collection;

import com.test.spring.boot.rest.greeting.model.Greeting;

public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOne(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);
}
