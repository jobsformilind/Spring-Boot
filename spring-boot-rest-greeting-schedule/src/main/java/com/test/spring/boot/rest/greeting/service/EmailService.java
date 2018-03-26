package com.test.spring.boot.rest.greeting.service;

import java.util.concurrent.Future;

import com.test.spring.boot.rest.greeting.model.Greeting;

public interface EmailService {

	Boolean sendSync(Greeting greeting);

	void sendAsync(Greeting greeting);

	Future<Boolean> sendAsyncwithResult(Greeting greeting);
}
