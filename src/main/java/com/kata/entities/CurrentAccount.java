package com.kata.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CurrentAccount extends Account{
	private double discovered;

	public CurrentAccount() {
		super();
	}

	public CurrentAccount(double discovered) {
		super();
		this.discovered = discovered;
	}
	
	public CurrentAccount(String codeAccount, Date dateCreation, double balance, Client client, double discovered) {
		super(codeAccount, dateCreation, balance, client);
		this.discovered = discovered;
	}

	public double getDiscovered() {
		return discovered;
	}

	public void setDiscovered(double discovered) {
		this.discovered = discovered;
	}
}
