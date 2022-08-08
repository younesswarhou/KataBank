package com.kata;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kata.dao.AccountDao;
import com.kata.dao.ClientDao;
import com.kata.dao.OperationDao;
import com.kata.entities.Account;
import com.kata.entities.Client;
import com.kata.entities.Operation;
import com.kata.entities.enums.OperationType;
import com.kata.service.IBankService;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Autowired
	private ClientDao clientDao;
	@Autowired
	private AccountDao AccountDao;
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private IBankService bankService;
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client client1 = clientDao.save(new Client("warhou","Youness", "younesswarhou@gmail.com","0664095192"))	;	
		Client client2 = clientDao.save(new Client("warhou","You", "younesswarhou@gmail.com","0664095192"))	;
		Client client3 = clientDao.save(new Client("war","You", "younesswarhou@gmail.com","0664095192"))	;
		
		Account account1 = AccountDao.save(new Account("cp1", new Date(), 90000, client1));
		Account account2 = AccountDao.save(new Account("cp2", new Date(), 6000, client2));
		Account account3 = AccountDao.save(new Account("D0192C11199C2005",new Date(),10000,client3));
		
		operationDao.save(new Operation(new Date(),9000, account1.getBalance() + 9000, OperationType.DEPOSIT , account1) );
		operationDao.save(new Operation(new Date(),6000, account1.getBalance() + 6000, OperationType.DEPOSIT , account1) );
		operationDao.save(new Operation(new Date(),2300, account1.getBalance() + 2300, OperationType.DEPOSIT , account1) );
		operationDao.save(new Operation(new Date(),9000, account1.getBalance() - 9000, OperationType.WITHDRAW , account1) );

		operationDao.save(new Operation(new Date(),2300, account2.getBalance() + 2300, OperationType.DEPOSIT , account2) );
		operationDao.save(new Operation(new Date(),400, account2.getBalance() + 400, OperationType.DEPOSIT , account2) );
		operationDao.save(new Operation(new Date(),2300, account2.getBalance() + 2300, OperationType.DEPOSIT , account2) );
		operationDao.save(new Operation(new Date(),3000, account2.getBalance() - 3000 , OperationType.WITHDRAW , account2) );

		operationDao.save(new Operation(new Date(),8500, account3.getBalance() + 8500 , OperationType.DEPOSIT , account3) );
		operationDao.save(new Operation(new Date(),6070, account3.getBalance() + 6070, OperationType.DEPOSIT , account3) );
		operationDao.save(new Operation(new Date(),2400, account3.getBalance() + 2400, OperationType.DEPOSIT , account3) );
		operationDao.save(new Operation(new Date(),1000, account3.getBalance() - 1000, OperationType.WITHDRAW , account3) );

		bankService.deposit("cp1", 111111);
		bankService.withdraw("D0192C11199C2005",2050);
		bankService.getOperationsList("cp1");
	}

}
