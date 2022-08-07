package com.kata.service;

import java.util.List;

import com.kata.entities.Account;
import com.kata.entities.Operation;

public interface IBankService {
	public Account getAccount(String codeAccount);
	public List<Account> deposit(String codeAccount, double amount);
	public List<Account> withdraw(String codeAccount, double amount);
	public List<Operation> getListOperations(String codeAccount);
}
