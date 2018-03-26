package com.test.spring.boot.rest.greeting.web;

import java.util.Collection;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.boot.rest.greeting.service.EmailService;
import com.test.spring.boot.rest.greeting.service.GreetingService;

@RestController
public class GreetingsController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GreetingService greetingService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingService.findAll();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
		Greeting greeting = greetingService.findOne(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		if (greeting == null || greeting.getId() == null) {
			return new ResponseEntity<Greeting>(HttpStatus.PARTIAL_CONTENT);
		}
		Greeting savedGreeting = greetingService.update(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable Long id) {
		greetingService.delete(id);
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/api/greetings/{id}/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sendGreeting(@PathVariable long id,
			@RequestParam(name = "wait", defaultValue = "false") boolean waitForAsyncResponse) {

		Greeting greeting = null;
		try {
			greeting = greetingService.findOne(id);
			if (greeting == null) {
				return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
			}

			if (waitForAsyncResponse) {
				Future<Boolean> asyncResponse = emailService.sendAsyncwithResult(greeting);
				boolean emailSent = asyncResponse.get();
				logger.info("greeting email sent? - {}", emailSent);
			} else {
				Boolean emailSent = emailService.sendSync(greeting);
				logger.info("greeting email sent? - {}", emailSent);
			}
		} catch (Exception ex) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

}
