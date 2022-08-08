package com.kata.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.dao.AccountDao;
import com.kata.dao.OperationDao;
import com.kata.exception.InsufficientBalanceException;
import com.kata.models.Account;
import com.kata.models.Operation;
import com.kata.models.enums.OperationType;
import com.kata.service.IBankService;

@Service
@Transactional
public class BankServiceImpl implements IBankService{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);
    
    private static final String DEPOSIT_ERROR = "Deposit operation not valid ";
    private static final String WITHDRAW_ERROR = "Withdraw operation not valid ";
    private static final String INSUFFICIENT_BALANCE = "The balance in not sufficient for account code ";
	
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
        LOGGER.info("Account code: ", accountCode);
		Account account = getAccount(accountCode);
		try {
			double balanceAfterOperation = 0;
			if(depositAmount > 0) {
				balanceAfterOperation = account.getBalance() + depositAmount;
			}
			Operation depositOperation = new Operation(new Date(), depositAmount, balanceAfterOperation, OperationType.DEPOSIT, account);
			operationDao.save(depositOperation);
			account.setBalance(balanceAfterOperation);
			accountDao.save(account);
		} catch (Exception e) {
			LOGGER.error(DEPOSIT_ERROR, e);
		}
		
		return account;
	}

	@Override
	public Account withdraw(String accountCode, double withdrawAmount) throws InsufficientBalanceException {
        LOGGER.info("Account code: ", accountCode);
		Account account = getAccount(accountCode);
		try {
			double balanceAfterOperation = 0;
			if(withdrawAmount < account.getBalance()) {
				balanceAfterOperation = account.getBalance() - withdrawAmount;
			}else {
	            throw new InsufficientBalanceException(INSUFFICIENT_BALANCE + ": "+ accountCode);
			}
			Operation withdrawOperation = new Operation(new Date(), withdrawAmount, balanceAfterOperation, OperationType.WITHDRAW, account);
			operationDao.save(withdrawOperation);
			account.setBalance(balanceAfterOperation);
			accountDao.save(account);
		} catch (Exception e) {
			LOGGER.error(WITHDRAW_ERROR, e);
		}
		
		return account;
	}

	@Override
	public List<Operation> getOperationsList(String accountCode) {
        LOGGER.info("Account code: ", accountCode);
		return operationDao.operationsList(accountCode);
	}
}
