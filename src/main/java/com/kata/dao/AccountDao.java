package com.kata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.entities.Account;

public interface AccountDao extends JpaRepository<Account, String>{
	public Account findByAccountCode(String codeAccount);
}
