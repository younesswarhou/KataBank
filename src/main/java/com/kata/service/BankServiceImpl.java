package com.kata.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.dao.AccountDao;
import com.kata.dao.OperationDao;
import com.kata.entities.Account;
import com.kata.entities.CurrentAccount;
import com.kata.entities.Depot;
import com.kata.entities.Operation;
import com.kata.entities.Retrait;

@Service
@Transactional
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OperationDao operationDao;

	@Override
	public Account getAccount(String codeAccount) {
		Optional<Account> compte = accountDao.findByCodeAccount(codeAccount);
		return compte.get();
	}

	@Override
	public List<Account> deposit(String codeAccount, double amount) {
		Account account = getAccount(codeAccount);
		Depot depot = new Depot(new Date(), amount, account);
		List<Depot> depots = Arrays.asList(depot);
		operationDao.saveAll(depots);
		account.setBalance(account.getBalance() + amount);
		List<Account> comptes = Arrays.asList(account);
		accountDao.saveAll(comptes);
		return comptes;
	}

	@Override
	public List<Account> withdraw(String codeAccount, double amount) {
		Account account = getAccount(codeAccount);
		double cashierFacility = 0;
		if(account instanceof CurrentAccount) {
			cashierFacility = ((CurrentAccount) account).getDiscovered();
		}
		if(account.getBalance() + cashierFacility < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		Retrait retrait = new Retrait(new Date(), amount, account);
		List<Retrait> retraits = Arrays.asList(retrait);
		operationDao.saveAll(retraits);
		account.setBalance(account.getBalance() - amount);
		List<Account> comptes = Arrays.asList(account);
		accountDao.saveAll(comptes);
		return comptes;
	}

	@Override
	public List<Operation> getListOperations(String codeAccount) {
		return operationDao.listOperations(codeAccount);
	}

}
