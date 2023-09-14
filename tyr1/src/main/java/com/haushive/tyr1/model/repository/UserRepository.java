package com.haushive.tyr1.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	
	public Optional<User> findByUsername(String username);
	
	public List<User> findAll();

}
