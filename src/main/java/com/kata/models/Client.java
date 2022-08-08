package com.kata.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable{

	private static final long serialVersionUID = -4107115194616602752L;
	@Id @GeneratedValue
	private Long code;
	private String lastName;
	private String firstName;
	private String mail;
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Account> accounts;

	public Client() {
		super();
	}

	public Client(String lastName, String firstName, String mail, String phone) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.phone = phone;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
}
