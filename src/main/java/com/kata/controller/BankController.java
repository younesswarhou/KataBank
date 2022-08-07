package com.kata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kata.entities.Account;
import com.kata.entities.Operation;
import com.kata.service.IBankService;

@RestController
public class BankController {
	@Autowired
	private IBankService bankService;
	
	@GetMapping("/getAccount/{codeAccount}")
	public ResponseEntity<Account> getAccount(@PathVariable("codeAccount") String codeAccount){
		Account account;
		if (codeAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
        	account = bankService.getAccount(codeAccount);
            }
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	@PostMapping(value="/saveOperation/{typeOperation}")
	public ResponseEntity<List<Account>> operationDepotAndRetrait(@PathVariable("typeOperation") String typeOperation, @RequestBody Operation operation){
		List<Account> accountList = null;
		try{
			if(typeOperation.equals("DEPOT")){
				accountList = bankService.deposit(operation.getAccount().getCodeAccount(), operation.getAmount());
			}
			else if(typeOperation.equals("RETRAIT")){
				accountList = bankService.withdraw(operation.getAccount().getCodeAccount(), operation.getAmount());
			} 
		} catch(Exception e) {
			throw e;
		}
		
		return new ResponseEntity<>(accountList, HttpStatus.CREATED);
	}
	
	@GetMapping("/getOperations/{codeAccount}")
	public ResponseEntity<List<Operation>> getOperations(@PathVariable("codeAccount") String codeAccount){
		List<Operation> operations;
		if (codeAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
        	operations = bankService.getListOperations(codeAccount);
            }
		return new ResponseEntity<>(operations, HttpStatus.OK);
	}
}
