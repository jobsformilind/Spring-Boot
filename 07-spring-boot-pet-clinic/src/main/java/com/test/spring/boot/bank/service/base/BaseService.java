package com.test.spring.boot.bank.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.test.spring.boot.bank.model.base.BaseEntity;
import com.test.spring.boot.bank.repository.base.BaseRepository;
import com.test.spring.boot.bank.service.exception.BankRuntimeException;

public abstract class BaseService<T extends BaseEntity> {
	private BaseRepository<T> repository;

	protected BaseService(BaseRepository<T> repository) {
		this.repository = repository;
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public T save(T obj) {
		return repository.save(obj);
	}

	public T findById(Serializable id) {
		Optional<T> dbOptional = repository.findById(id);
		if (!dbOptional.isPresent()) {
			throw new BankRuntimeException(404, "error.entity.not.found", "Entity not found.");
		}
		return dbOptional.get();
	}

	public void delete(Serializable id) {
		repository.deleteById(id);
	}
}
