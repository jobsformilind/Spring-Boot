package com.test.spring.boot.bank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.test.spring.boot.bank.model.base.NamedEntity;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {
	private static final long serialVersionUID = 991350153398332239L;

}
