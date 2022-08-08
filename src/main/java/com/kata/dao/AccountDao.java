package com.kata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.models.Account;

public interface AccountDao extends JpaRepository<Account, String>{
	public Account findByAccountCode(String codeAccount);
}
