package com.kata.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Account implements Serializable{

	private static final long serialVersionUID = -6441773608178479307L;
	@Id @GeneratedValue
	private Long code;
	private String codeAccount;
	private Date dateCreation;
	private double balance;
	
	@ManyToOne
	@JoinColumn(name="CODE_CLIENT")
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private Collection<Operation> operations;
	
	public Account() {
		super();
	}

	public Account(String codeAccount, Date dateCreation, double balance, Client client) {
		super();
		this.codeAccount = codeAccount;
		this.dateCreation = dateCreation;
		this.balance = balance;
		this.client = client;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getCodeAccount() {
		return codeAccount;
	}

	public void setCodeAccount(String codeAccount) {
		this.codeAccount = codeAccount;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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
