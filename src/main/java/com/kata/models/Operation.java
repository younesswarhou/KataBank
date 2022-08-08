package com.kata.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kata.models.enums.OperationType;

@Entity
public class Operation implements Serializable{

	private static final long serialVersionUID = -1670230618008788389L;
	@Id @GeneratedValue
	private Long code;
	private Date operationDate;
	private double amount;
	private double balanceAfterOperation;
	@Enumerated(EnumType.STRING)
    private OperationType operationType;

	@ManyToOne
	@JoinColumn(name="AccountCode")
	private Account account;

	public Operation() {
		super();
	}

	public Operation(Date operationDate, double amount, double balanceAfterOperation, OperationType operationType,
			Account account) {
		super();
		this.operationDate = operationDate;
		this.amount = amount;
		this.balanceAfterOperation = balanceAfterOperation;
		this.operationType = operationType;
		this.account = account;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalanceAfterOperation() {
		return balanceAfterOperation;
	}

	public void setBalanceAfterOperation(double balanceAfterOperation) {
		this.balanceAfterOperation = balanceAfterOperation;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
