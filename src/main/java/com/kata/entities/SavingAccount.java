package com.kata.entities;

import java.util.Date;

public class SavingAccount extends Account {
	private double rate;
	
	public SavingAccount() {
		super();
	}

	public SavingAccount(double rate) {
		super();
		this.rate = rate;
	}
	
	public SavingAccount(String codeAccount, Date dateCreation, double balance, Client client, double rate) {
		super(codeAccount, dateCreation, balance, client);
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
