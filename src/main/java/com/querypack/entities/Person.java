package com.querypack.entities;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Person extends Base {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Transient
	public String getFullName() {
		return String.format("%s %s", this.firstName, this.lastName);
	}
}
