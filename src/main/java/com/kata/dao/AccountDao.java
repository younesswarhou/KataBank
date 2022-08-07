package com.kata.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.entities.Account;

public interface AccountDao extends JpaRepository<Account, String>{
	public Optional<Account> findByCodeAccount(String codeAccount);
}
