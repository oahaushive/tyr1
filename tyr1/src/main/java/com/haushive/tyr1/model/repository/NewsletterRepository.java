package com.haushive.tyr1.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.tyr1.model.domain.Newsletter;

@Repository
public interface NewsletterRepository extends CrudRepository<Newsletter, Integer>{
	
	public List<Newsletter> findAll(); 

}
