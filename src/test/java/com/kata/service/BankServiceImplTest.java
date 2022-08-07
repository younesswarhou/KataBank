package com.kata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.CollectionUtils;

import com.kata.dao.AccountDao;
import com.kata.dao.OperationDao;
import com.kata.entities.Account;
import com.kata.entities.Client;
import com.kata.entities.Depot;
import com.kata.entities.Operation;
import com.kata.entities.Retrait;

public class BankServiceImplTest extends AbstractMockitoTest {
	
	@InjectMocks
	private BankServiceImpl bankServiceImpl;
	
	@Mock
	private AccountDao accountDao;
	@Mock
	private OperationDao operationDao;
	
	List<Operation> operations = new ArrayList<>();
	
	@BeforeEach
	void testConsulter() {
        Mockito.when(accountDao.findByCodeAccount(Mockito.any())).thenReturn(account());
        
        Account compte = bankServiceImpl.getAccount("dx10000a");
        
        Assertions.assertEquals(compte.getCodeAccount(),account().get().getCodeAccount());
	}
	
	@Test
	void testDepot() {
        Mockito.when(operationDao.save(Mockito.any())).thenReturn(operationDepot());
        Mockito.when(accountDao.save(Mockito.any())).thenReturn(account());
        
        List<Account> comptes = bankServiceImpl.deposit("dx10000a", 3000.0);  
        
        Assertions.assertFalse(CollectionUtils.isEmpty(comptes));
	}
	
	@Test
	void testRetirer() {
        Mockito.when(operationDao.save(Mockito.any())).thenReturn(operationRetrait());
        Mockito.when(accountDao.save(Mockito.any())).thenReturn(account());
        
        List<Account> comptes = bankServiceImpl.withdraw("dx10000a", 1000.0);
        
        Assertions.assertFalse(CollectionUtils.isEmpty(comptes));
	}
	
	@Test
	void testGetListeOperations() {
        Mockito.when(operationDao.listOperations(Mockito.any())).thenReturn(operations());
        
        List<Operation> operations = bankServiceImpl.getListOperations("dx10000a");
        
        Assertions.assertFalse(CollectionUtils.isEmpty(operations));
	}
	
	private Client client() {
		Client client = new Client();
		client.setCode(1L);
		client.setMail("younesswarhou@gmail.com");
		client.setLastName("warhou");
		client.setFirstName("Youness");
		client.setPhone("0664095192");
		return client;
	}
	
	private Optional<Account> account() {
		Account account = new Account();
		account.setClient(client());
		account.setCodeAccount("dx10000a");
		account.setDateCreation(new Date());
		account.setBalance(6000.0);
		account.setOperations(operations);
		return Optional.of(account);
	}
	
	private List<Operation> operationRetrait(){
		Operation operationRetrait = new Retrait();
		operationRetrait.setAccount(account().get());
		operationRetrait.setDateOperation(new Date());
		operationRetrait.setAmount(600.0);
		operationRetrait.setNumber(1L);
		operations.add(operationRetrait);
		return operations;
	}
	
	private List<Operation> operationDepot(){
		Operation operationDepot = new Depot();
		List<Operation> operations = new ArrayList<>();
		operationDepot.setAccount(account().get());
		operationDepot.setDateOperation(new Date());
		operationDepot.setAmount(1000.0);
		operationDepot.setNumber(1L);
		operations.add(operationDepot);
		return operations;
	}
	
	private List<Operation> operations(){
		List<Operation> operationList = new ArrayList<>();
		operationList.addAll(operationDepot());
		operationList.addAll(operationRetrait());
		return operationList;
	}
}
