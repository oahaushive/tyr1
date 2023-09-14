package com.haushive.tyr1.graphql;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.haushive.tyr1.model.domain.Crypto;
import com.haushive.tyr1.model.domain.Newsletter;
import com.haushive.tyr1.model.domain.Pool;
import com.haushive.tyr1.model.domain.PoolDetail;
import com.haushive.tyr1.model.domain.User;
import com.haushive.tyr1.model.service.CryptoService;
import com.haushive.tyr1.model.service.NewsletterService;
import com.haushive.tyr1.model.service.PoolService;
import com.haushive.tyr1.model.service.UserService;
import com.haushive.tyr1.security.AdminSecured;
import com.haushive.tyr1.security.Unsecured;

@Component
public class Tyr1MutationResolver implements GraphQLMutationResolver {
	
	@Autowired
	private CryptoService cryptoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private PoolService poolService;
	
	@Autowired 
	private NewsletterService newsletterService;
	
	public List<Crypto> addNewCrypto(List<Crypto> input){
		cryptoService.insertNewCrypto(input);
		return input;
	}
	
	@Unsecured
	public User createUser(User user) {
        return userService.createUser(user);
    }
	
	@AdminSecured
	public User deleteUser(String username) {
	   return userService.deleteUser(username);
	}
	
	public User updatePassword(String username, String password) {
		return userService.updatePassword(username, password);
	}
	
	public Pool createPool(List<PoolDetail> details) throws IOException {
		return poolService.insertNewPool(details);
	}
	
	public Pool updatePool(List<PoolDetail> details, String poolId) throws IOException {
		return poolService.updatePool(details, poolId);
	}
	
	public Pool deletePool(String poolId) {
		return poolService.deletePoolWithDetails(poolId);
	}
	
	public Pool deletePoolDetail(String poolId, String currencySymbol) throws IOException {
		return poolService.deletePoolDetail(poolId, currencySymbol);
	}
	
	public User updateUser(String username, User user) {
		return userService.updateUser(username, user);
	}
	
	public User addUpdateDeletePoolUser(User user, String operation) {
		if(operation.equals("add")) {
			userService.insertPoolDetail(user);
		}
		if(operation.equals("delete")) {
			userService.deletePoolDetail(user);
		}	
		if(operation.equals("update")) {
			userService.updatePoolDetail(user);
		}
		User userToReturn = userService.getUser(user.getUsername());
		return userToReturn;
	}
	
	public Newsletter updateNewsletter(Newsletter newsletter) {
		return newsletterService.saveNewsletter(newsletter);
	}

}
