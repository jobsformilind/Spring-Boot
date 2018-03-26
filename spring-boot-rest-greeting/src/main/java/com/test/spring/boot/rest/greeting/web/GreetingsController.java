package com.test.spring.boot.rest.greeting.web;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.rest.greeting.model.Greeting;

@RestController
public class GreetingsController {
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingsMap;

	static {
		save(new Greeting("Hello_One"));
		save(new Greeting("Hello_Two"));
		save(new Greeting("Hello_Three"));
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

	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingsMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") BigInteger id) {
		Greeting greeting = greetingsMap.get(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting savedGreeting = save(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		if (greeting == null || greeting.getId() == null) {
			return new ResponseEntity<Greeting>(HttpStatus.PARTIAL_CONTENT);
		}
		Greeting savedGreeting = save(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable BigInteger id) {
		Greeting greeting = greetingsMap.remove(id);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.NO_CONTENT);
	}


	
	
}
