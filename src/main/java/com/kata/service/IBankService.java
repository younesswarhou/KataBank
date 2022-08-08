package com.kata.service;

import java.util.List;

import com.kata.entities.Account;
import com.kata.entities.Operation;
import com.kata.exception.InsufficientBalanceException;

public interface IBankService {
	public Account getAccount(String accountCode);
	public Account deposit(String accountCode, double depositAmount);
	public Account withdraw(String accountCode, double withdrawAmount) throws InsufficientBalanceException;
	public List<Operation> getOperationsList(String accountCode);
}
