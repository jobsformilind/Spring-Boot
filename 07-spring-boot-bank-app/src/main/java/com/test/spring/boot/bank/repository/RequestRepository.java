package com.test.spring.boot.bank.repository;

import org.springframework.stereotype.Repository;

import com.test.spring.boot.bank.model.Request;
import com.test.spring.boot.bank.repository.base.BaseRepository;

@Repository
public interface RequestRepository extends BaseRepository<Request> {
}
