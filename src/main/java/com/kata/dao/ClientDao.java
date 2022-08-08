package com.kata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.models.Client;

public interface ClientDao extends JpaRepository<Client, Long>{

}
