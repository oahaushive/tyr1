package com.haushive.tyr1.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.Pool;

@Repository
public interface PoolRepository extends CrudRepository<Pool, String>{
	
	public Pool findByPoolId(String poolId);
	
	public List<Pool> findAll();

}
