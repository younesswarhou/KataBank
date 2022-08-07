package com.kata;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kata.dao.AccountDao;
import com.kata.dao.ClientDao;
import com.kata.dao.OperationDao;
import com.kata.entities.Account;
import com.kata.entities.Client;
import com.kata.entities.CurrentAccount;
import com.kata.entities.Depot;
import com.kata.entities.Operation;
import com.kata.entities.Retrait;
import com.kata.entities.SavingAccount;
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
		
		Account account1 = AccountDao.save(new CurrentAccount("cp1", new Date(), 90000, client1, 6000));
		Account account2 = AccountDao.save(new CurrentAccount("cp2", new Date(), 6000, client2, 5000));
		Account account3 = AccountDao.save(new CurrentAccount("D0192C11199C2005",new Date(),10000,client3,7800));
		
		operationDao.save(new Depot(new Date(),9000 , account1) );
		operationDao.save(new Depot(new Date(),6000 , account1) );
		operationDao.save(new Depot(new Date(),2300 , account1) );
		operationDao.save(new Retrait(new Date(),9000 , account1) );

		operationDao.save(new Depot(new Date(),2300 , account2) );
		operationDao.save(new Depot(new Date(),400 , account2) );
		operationDao.save(new Depot(new Date(),2300 , account2) );
		operationDao.save(new Retrait(new Date(),3000 , account2) );

		operationDao.save(new Depot(new Date(),8500 , account3) );
		operationDao.save(new Depot(new Date(),6070 , account3) );
		operationDao.save(new Depot(new Date(),2400 , account3) );
		operationDao.save(new Retrait(new Date(),1000 , account3) );

		bankService.deposit("cp1", 111111);
		bankService.withdraw("D0192C11199C2005",2050);
		bankService.getListOperations("cp1");
	}

}
