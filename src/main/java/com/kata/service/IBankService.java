package com.kata.service;

import java.util.List;

import com.kata.exception.InsufficientBalanceException;
import com.kata.models.Account;
import com.kata.models.Operation;

public interface IBankService {
	public Account getAccount(String accountCode);
	public Account deposit(String accountCode, double depositAmount);
	public Account withdraw(String accountCode, double withdrawAmount) throws InsufficientBalanceException;
	public List<Operation> getOperationsList(String accountCode);
}
