package com.haushive.tyr1.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.tyr1.model.domain.PoolUser;
import com.haushive.tyr1.model.domain.User;
import com.haushive.tyr1.model.repository.PoolUserRepository;

@Service
public class PoolUserService {
	
	@Autowired
	private PoolUserRepository poolUserRepository;
	
	@Autowired
	private UserService userService;
	
	public void updatePoolUser(String username, String updateUsername) {
		User user = userService.getUser(username);
		List<PoolUser> poolDetails = user.getPoolDetails();
		poolDetails.stream().forEach(detail -> {
			detail.setUsername(updateUsername);
		});
		poolUserRepository.saveAll(poolDetails);
	}

}
