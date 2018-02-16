package com.test.spring.boot.bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.spring.boot.bank.model.base.Person;
import com.test.spring.boot.bank.util.BankUtils;

@Entity
@Table(name = "bankers")
public class Banker extends Person {
	private static final long serialVersionUID = 9188935163569070247L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "banker_specialties", joinColumns = @JoinColumn(name = "banker_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;

	@JsonIgnore
	protected Set<Specialty> getSpecialtiesInternal() {
		this.specialties = BankUtils.ensureNotNullHashSet(this.specialties);
		return this.specialties;
	}

	protected void setSpecialtiesInternal(Set<Specialty> specialties) {
		this.specialties = specialties;
	}

	@XmlElement
	public List<Specialty> getSpecialties() {
		List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}

	@JsonIgnore
	public int getNrOfSpecialties() {
		return getSpecialtiesInternal().size();
	}

	public void addSpecialty(Specialty specialty) {
		getSpecialtiesInternal().add(specialty);
	}

}
