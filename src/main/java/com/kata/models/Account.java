package com.kata.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account implements Serializable{

	private static final long serialVersionUID = -6441773608178479307L;
	@Id @GeneratedValue
	private Long code;
	private String accountCode;
	private Date creationDate;
	private double balance;
	
	@ManyToOne
	@JoinColumn(name="clientCode")
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private Collection<Operation> operations;
	
	public Account() {
		super();
	}

	public Account(String accountCode, Date creationDate, double balance, Client client) {
		super();
		this.accountCode = accountCode;
		this.creationDate = creationDate;
		this.balance = balance;
		this.client = client;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

}
