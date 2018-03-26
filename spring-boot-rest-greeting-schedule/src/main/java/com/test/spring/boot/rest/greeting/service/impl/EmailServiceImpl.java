package com.test.spring.boot.rest.greeting.service.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.test.spring.boot.rest.greeting.async.AsyncResponse;
import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.boot.rest.greeting.service.EmailService;
import com.test.spring.utils.ThreadUtils;

@Service
public class EmailServiceImpl implements EmailService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private long pause = 5000;

	public Boolean sendSync(Greeting greeting) {
		logger.info("Entering sendSync");

		Boolean success = Boolean.FALSE;
		ThreadUtils.sleepSilently(pause);
		logger.info("Processing time was {} seconds.", pause / 1000);
		success = Boolean.TRUE;

		logger.info("Exiting sendSync");
		return success;
	}

	@Async
	public void sendAsync(Greeting greeting) {
		logger.info("Entering sendAsync");
		sendSync(greeting);
		logger.info("Exiting sendAsync");
	}

	@Async
	public Future<Boolean> sendAsyncwithResult(Greeting greeting) {
		logger.info("Entering sendAsyncwithResult");

		AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();
		try {
			Boolean success = sendSync(greeting);
			response.complete(success);
		} catch (Exception e) {
			logger.warn("Exception caught sending asynchronous mail.", e);
			response.completeExceptionally(e);
		}

		logger.info("Exiting sendAsyncwithResult");
		return response;
	}

}
