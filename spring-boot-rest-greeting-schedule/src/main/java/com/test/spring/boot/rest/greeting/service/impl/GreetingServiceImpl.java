package com.test.spring.boot.rest.greeting.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.spring.boot.rest.greeting.model.Greeting;
import com.test.spring.boot.rest.greeting.repository.GreetingRepository;
import com.test.spring.boot.rest.greeting.service.GreetingService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceImpl implements GreetingService {
	@Autowired
	GreetingRepository repository;

	public Collection<Greeting> findAll() {
		return repository.findAll();
	}

	@Cacheable(value = "greetings", key = "#id")
	public Greeting findOne(Long id) {
		//ThreadUtils.sleepSilently(5000);
		return repository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#result.id")
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			return null;
		}
		return repository.save(greeting);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#greeting.id")
	public Greeting update(Greeting greeting) {
		Greeting greetingSaved = findOne(greeting.getId());
		if (greetingSaved == null) {
			return null;
		}
		if (Long.valueOf(1L).equals(greeting.getId())) {
			throw new RuntimeException("GO BACK !!!!");
		}
		return repository.save(greeting);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greetings", key = "#id")
	public void delete(Long id) {
		repository.delete(id);
	}

	@CacheEvict(value = "greetings", allEntries = true)
	public void evictCache() {
	}
}
