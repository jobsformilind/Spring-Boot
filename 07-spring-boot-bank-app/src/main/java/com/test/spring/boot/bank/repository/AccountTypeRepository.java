package com.test.spring.boot.bank.repository;

import org.springframework.stereotype.Repository;

import com.test.spring.boot.bank.model.AccountType;
import com.test.spring.boot.bank.repository.base.BaseRepository;

@Repository
public interface AccountTypeRepository extends BaseRepository<AccountType> {

}
