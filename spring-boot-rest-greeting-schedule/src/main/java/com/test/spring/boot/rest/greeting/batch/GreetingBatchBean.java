package com.test.spring.boot.rest.greeting.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.boot.rest.greeting.service.GreetingService;

@Profile("batch")
@Component
public class GreetingBatchBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	GreetingService greetingService;

	@Scheduled(cron = "${batch.greeting.cron}")
	public void cronjob() {
		logger.info("Entering cronjob.");
		Collection<Greeting> greetings = greetingService.findAll();
		logger.info("There are {} greetings in the database.", greetings.size());
		logger.info("Exiting cronjob.");
	}

	@Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedRateString = "${batch.greeting.fixedRate}")
	public void fixedRateExecution() {
		logger.info("Entering fixedRateExecution.");
		Collection<Greeting> greetings = greetingService.findAll();
		logger.info("There are {} greetings in the database.", greetings.size());
		logger.info("Exiting fixedRateExecution.");
	}

	@Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedDelayString = "${batch.greeting.fixedDelay}")
	public void fixedDelayExecution() {
		logger.info("Entering fixedDelayExecution.");
		Collection<Greeting> greetings = greetingService.findAll();
		logger.info("There are {} greetings in the database.", greetings.size());
		logger.info("Exiting fixedDelayExecution.");
	}
}
