package com.kata.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Depot extends Operation {
	
	public Depot(Date dateOperation, double amount, Account account) {
		super(dateOperation, amount, account);
	}

	public Depot() {
		super();
	}
}
