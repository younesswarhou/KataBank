package com.kata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.CollectionUtils;

import com.kata.dao.AccountDao;
import com.kata.dao.OperationDao;
import com.kata.exception.InsufficientBalanceException;
import com.kata.models.Account;
import com.kata.models.Client;
import com.kata.models.Operation;
import com.kata.models.enums.OperationType;
import com.kata.service.impl.BankServiceImpl;

public class BankServiceImplTest extends AbstractMockitoTest {
	
	@InjectMocks
	private BankServiceImpl bankServiceImpl;
	
	@Mock
	private AccountDao accountDao;
	@Mock
	private OperationDao operationDao;
		
	@BeforeEach
	void testConsulter() {
        Mockito.when(accountDao.findByAccountCode(Mockito.any())).thenReturn(account());
        
        Account compte = bankServiceImpl.getAccount("dx10000a");
        
        Assertions.assertNotNull(compte.getAccountCode());
        Assertions.assertEquals(compte.getAccountCode(),account().getAccountCode());
	}
	
	@Test
	void testDeposit() {
        Mockito.when(operationDao.save(Mockito.any())).thenReturn(operationDeposit());
        Mockito.when(accountDao.save(Mockito.any())).thenReturn(account());
        
        Account account = bankServiceImpl.deposit(account().getAccountCode(), 3000.0);  
        
        Assertions.assertNotNull(account.getAccountCode());
        Assertions.assertEquals(account.getAccountCode(), "dx10000a");
        Assertions.assertEquals(account.getBalance(), 9000);
	}
	
	@Test
	void testWithdraw() throws InsufficientBalanceException {
        Mockito.when(operationDao.save(Mockito.any())).thenReturn(operationWithdraw());
        Mockito.when(accountDao.save(Mockito.any())).thenReturn(account());
        
        Account account = bankServiceImpl.withdraw("dx10000a", 1000.0);
        
        Assertions.assertNotNull(account.getAccountCode());
        Assertions.assertEquals(account.getAccountCode(), "dx10000a");
        Assertions.assertEquals(account.getBalance(), 5000);
	}
	
	@Test
	void testGetListeOperations() {
        Mockito.when(operationDao.operationsList(Mockito.any())).thenReturn(operations());
        
        List<Operation> operations = bankServiceImpl.getOperationsList("dx10000a");
        
        Assertions.assertEquals(1000, operations.get(0).getAmount());
        Assertions.assertEquals("dx10000a", operations.get(0).getAccount().getAccountCode());
        Assertions.assertEquals(OperationType.DEPOSIT, operations.get(0).getOperationType());
        Assertions.assertEquals(600, operations.get(1).getAmount());
        Assertions.assertEquals("dx10000a", operations.get(1).getAccount().getAccountCode());
        Assertions.assertEquals(OperationType.WITHDRAW, operations.get(1).getOperationType());
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
	
	private Account account() {
		Account account = new Account();
		account.setCode(1L);
		account.setClient(client());
		account.setAccountCode("dx10000a");
		account.setCreationDate(new Date());
		account.setBalance(6000.0);
		return account;
	}
	
	private Operation operationWithdraw(){
		Operation operationWithdraw = new Operation();
		operationWithdraw.setAccount(account());
		operationWithdraw.setOperationDate(new Date());
		operationWithdraw.setAmount(600.0);
		operationWithdraw.setCode(1L);
		operationWithdraw.setOperationType(OperationType.WITHDRAW);
		return operationWithdraw;
	}
	
	private Operation operationDeposit(){
		Operation operationDeposit = new Operation();
		operationDeposit.setAccount(account());
		operationDeposit.setOperationDate(new Date());
		operationDeposit.setAmount(1000.0);
		operationDeposit.setCode(1L);
		operationDeposit.setOperationType(OperationType.DEPOSIT);
		return operationDeposit;
	}
	
	private List<Operation> operations(){
		List<Operation> operationList = new ArrayList<>();
		operationList.add(operationDeposit());
		operationList.add(operationWithdraw());
		return operationList;
	}
}
