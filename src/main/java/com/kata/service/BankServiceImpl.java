package com.kata.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.dao.AccountDao;
import com.kata.dao.OperationDao;
import com.kata.entities.Account;
import com.kata.entities.Operation;
import com.kata.entities.enums.OperationType;
import com.kata.exception.InsufficientBalanceException;

@Service
@Transactional
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OperationDao operationDao;

	@Override
	public Account getAccount(String accountCode) {
		Account compte = accountDao.findByAccountCode(accountCode);
		return compte;
	}

	@Override
	public Account deposit(String accountCode, double depositAmount) {
		Account account = getAccount(accountCode);
		double balanceAfterOperation = 0;
		if(depositAmount > 0) {
			balanceAfterOperation = account.getBalance() + depositAmount;
		}
		Operation depositOperation = new Operation(new Date(), depositAmount, balanceAfterOperation, OperationType.DEPOSIT, account);
		operationDao.save(depositOperation);
		account.setBalance(balanceAfterOperation);
		accountDao.save(account);
		return account;
	}

	@Override
	public Account withdraw(String codeAccount, double withdrawAmount) throws InsufficientBalanceException {
		Account account = getAccount(codeAccount);
		double balanceAfterOperation = 0;
		if(withdrawAmount < account.getBalance()) {
			balanceAfterOperation = account.getBalance() - withdrawAmount;
		}else {
            throw new InsufficientBalanceException("The balance in not sufficient for account code :"+ codeAccount);
		}
		Operation withdrawOperation = new Operation(new Date(), withdrawAmount, balanceAfterOperation, OperationType.WITHDRAW, account);
		operationDao.save(withdrawOperation);
		account.setBalance(balanceAfterOperation);
		accountDao.save(account);
		return account;
	}

	@Override
	public List<Operation> getOperationsList(String accountCode) {
		return operationDao.operationsList(accountCode);
	}
}
