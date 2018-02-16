package com.test.spring.boot.bank.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BaseEntity {
	private static final long serialVersionUID = -1118715369988767144L;

	@Column(name = "name")
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getName();
	}
}
