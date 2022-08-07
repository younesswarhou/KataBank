package com.kata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kata.entities.Operation;

public interface OperationDao extends JpaRepository<Operation, Long>{
	@Query("select o from Operation o where o.account.codeAccount =:codeAccount order by o.dateOperation desc")
	public List<Operation> listOperations(@Param("codeAccount")String codeAccount);
}
