package com.test.spring.boot.bank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.test.spring.boot.bank.model.base.NamedEntity;

@Entity
@Table(name = "account_types")
public class AccountType extends NamedEntity {
	private static final long serialVersionUID = -1165851191847297894L;

}
