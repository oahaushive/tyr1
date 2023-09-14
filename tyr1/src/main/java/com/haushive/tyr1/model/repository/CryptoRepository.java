package com.haushive.tyr1.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.Crypto;

@Repository
public interface CryptoRepository extends CrudRepository<Crypto, String>{
	
	public List<Crypto> findAll();

}
