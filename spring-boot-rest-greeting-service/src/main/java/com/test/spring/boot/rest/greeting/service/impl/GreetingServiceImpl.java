package com.test.spring.boot.rest.greeting.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.boot.rest.greeting.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService {
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingsMap;

	static {
		save(new Greeting("Hello_One"));
		save(new Greeting("Hello_Two"));
	}

	private static Greeting save(Greeting greeting) {
		if (greetingsMap == null) {
			greetingsMap = new HashMap<BigInteger, Greeting>();
			nextId = BigInteger.ONE;
		}

		if (greeting.getId() == null) {
			greeting.setId(nextId);
			nextId = nextId.add(BigInteger.ONE);
			greetingsMap.put(greeting.getId(), greeting);
		} else {
			Greeting savedGreeting = greetingsMap.get(greeting.getId());
			savedGreeting.setText(greeting.getText());
		}
		return greeting;
	}

	private static boolean remove(Long id) {
		Greeting deletedGreeting = greetingsMap.remove(id);
		if (deletedGreeting == null) {
			return false;
		}
		return true;
	}

	public Collection<Greeting> findAll() {
		return greetingsMap.values();
	}

	public Greeting findOne(Long id) {
		return greetingsMap.get(id);
	}

	public Greeting create(Greeting greeting) {
		return save(greeting);
	}

	public Greeting update(Greeting greeting) {
		return save(greeting);
	}

	public void delete(Long id) {
		remove(id);
	}
}
