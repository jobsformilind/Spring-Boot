package com.test.spring.boot.bank.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.spring.boot.bank.model.base.Person;
import com.test.spring.boot.bank.util.BankUtils;

@Entity
@Table(name = "customers")
public class Customer extends Person {
	private static final long serialVersionUID = -4070279112601937902L;

	@Column(name = "address")
	@NotEmpty
	private String address;

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
	private Set<Account> accounts;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@JsonIgnore
	protected Set<Account> getAccountsInternal() {
		this.accounts = BankUtils.ensureNotNullHashSet(this.accounts);
		return this.accounts;
	}

	protected void setAccountsInternal(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public String toString() {
		return new ToStringCreator(this).append("id", this.getId()).append("new", this.isNew())
				.append("lastName", this.getLastName()).append("firstName", this.getFirstName())
				.append("address", this.address).append("city", this.city).append("telephone", this.telephone)
				.toString();
	}
}
