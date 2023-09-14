package com.haushive.tyr1.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.PoolUser;

@Repository
public interface PoolUserRepository extends CrudRepository<PoolUser, String>{
	
	public List<PoolUser> findByUsername(String poolId);

}
