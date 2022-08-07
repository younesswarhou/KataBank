package com.kata.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Retrait extends Operation {
	
	public Retrait(Date dateOperation, double amount, Account account) {
		super(dateOperation, amount, account);
	}

	public Retrait() {
		super();
	}
}
