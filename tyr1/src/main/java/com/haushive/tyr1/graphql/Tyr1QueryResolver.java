package com.haushive.tyr1.graphql;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.haushive.tyr1.exception.WebApplicationException;
import com.haushive.tyr1.model.domain.Newsletter;
import com.haushive.tyr1.model.domain.Pool;
import com.haushive.tyr1.model.domain.User;
import com.haushive.tyr1.model.repository.UserRepository;
import com.haushive.tyr1.model.service.NewsletterService;
import com.haushive.tyr1.model.service.PoolService;
import com.haushive.tyr1.model.service.UserService;

@Component
public class Tyr1QueryResolver implements GraphQLQueryResolver{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PoolService poolService;
	
	@Autowired
	private NewsletterService newsletterService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public User fetchByUsername(String username) {
		Optional<User> userToFetch = userRepository.findByUsername(username);
		logger.debug("fetching for username: " + username);
		if(userToFetch.isEmpty()) {
			throw new WebApplicationException("Username: " + username + " not found.");
		}
		User user = userToFetch.get();
		return user;
	}
	
	public User getUser(String username, String password) {
        return userService.getUser(username, password); 
    }
	
	public Pool getPoolWithDetails(String poolId) throws IOException {
		return poolService.findPoolWithDetails(poolId);
	}
	
	public List<Pool> getPools(){
		return poolService.getPools();
	}
	
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	public Newsletter getNewsletter() {
		return newsletterService.getNewsletter();
	}

}
