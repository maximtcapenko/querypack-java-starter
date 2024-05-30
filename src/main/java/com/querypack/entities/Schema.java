package com.querypack.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.querypack.entities.Enums.SchemaState;

@Entity
public class Schema extends Base {

	private String name;
	private String registrationCode;
	private SchemaState state;
	private Person contactPerson;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	@Enumerated(EnumType.ORDINAL)
	public SchemaState getState() {
		return state;
	}

	public void setState(SchemaState state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Person getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}
}
