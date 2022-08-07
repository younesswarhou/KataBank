package com.kata.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Operation implements Serializable{

	private static final long serialVersionUID = -1670230618008788389L;
	@Id @GeneratedValue
	private Long number;
	private Date dateOperation;
	private double amount;

	@ManyToOne
	@JoinColumn(name="CODE_ACCOUNT")
	private Account account;

	public Operation() {
		super();
	}

	public Operation(Date dateOperation, double amount, Account account) {
		super();
		this.dateOperation = dateOperation;
		this.amount = amount;
		this.account = account;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
