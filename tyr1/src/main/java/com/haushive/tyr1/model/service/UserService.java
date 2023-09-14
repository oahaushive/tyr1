package com.haushive.tyr1.model.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.tyr1.exception.UserAlreadyExistsException;
import com.haushive.tyr1.exception.UserNotFoundException;
import com.haushive.tyr1.exception.WebApplicationException;
import com.haushive.tyr1.model.domain.Pool;
import com.haushive.tyr1.model.domain.PoolUser;
import com.haushive.tyr1.model.domain.User;
import com.haushive.tyr1.model.repository.PoolRepository;
import com.haushive.tyr1.model.repository.PoolUserRepository;
import com.haushive.tyr1.model.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PoolUserRepository poolUserRepository;
	
	@Autowired
	private PoolService poolService;
	
	@Autowired 
	private PoolRepository poolRepository;
	
	@Autowired
	private PoolUserService poolUserService;

    public User createUser(User user) {
        if (userExists(user.getUsername())) {
        	throw new UserAlreadyExistsException("A user already exists with this username, please try another one");
        }
        userRepository.save(user);
        if(!user.getPoolDetails().isEmpty()) {
        	user.getPoolDetails().stream().forEach(detail -> {
        		detail.setUsername(user.getUsername());
        	});
            poolUserRepository.saveAll(user.getPoolDetails());
        }
        return user;
    }

    public User getUser(String username, String password) {
    	Optional<User> userToFind = userRepository.findByUsername(username);
        if (userExists(username) && userToFind.get().getUsername().equals(username) && userToFind.get().getPassword().equals(password)) {
        	User userToReturn = userToFind.get();
        	userToReturn.setPoolDetails(poolUserRepository.findByUsername(userToReturn.getUsername()));
        	userToReturn.getPoolDetails().stream().forEach(detail -> {
    			if(detail.getPoolId() != null) {
    				setUserAmount(userToReturn);
    				System.out.println("pool: " + detail.getPoolId() + " user service " + LocalDateTime.now());
    			}
    		});
        	return userToReturn;
        }
        throw new WebApplicationException("We were unable to find a user for: " + username);
    }
    
    public void scheduler() throws InterruptedException {
    	TimerTask timerTask = new TimerTask() {
    		public void run() {
              
            }
    	};
    	Timer timer = new Timer();
    	timer.schedule(timerTask, 10000);
    }
    
    public User getUser(String username) {
    	if (userExists(username)){
        	Optional<User> userToFind = userRepository.findByUsername(username);
        	User userToReturn = userToFind.get();
        	userToReturn.setPoolDetails(poolUserRepository.findByUsername(userToReturn.getUsername()));
        	setUserAmount(userToReturn);
        	return userToReturn;
    	}
    	throw new WebApplicationException("We were unable to find a user for: " + username);
    }

    private boolean userExists(String username) {
        Optional<User> userToFind = userRepository.findByUsername(username);
        if (!userToFind.isEmpty() && userToFind.get().getUsername().equals(username)) {
        	return true;
        }
        return false;
    }
    
    public User deleteUser(String username) {
    	if(!userExists(username)) {
    		throw new UserNotFoundException("We were unable to find a user for: ", username);
    	}
    	Optional<User> userToDelete = userRepository.findByUsername(username);
    	User user = userToDelete.get();
    	user.setPoolDetails(poolUserRepository.findByUsername(username));
    	poolUserRepository.deleteAll(user.getPoolDetails());
    	userRepository.delete(userToDelete.get());
    	return user;
    }
    
    public User updateUser(String username, User userToUpdate) {
    	User user = getUser(username);
    	if(userToUpdate.getEmail() != null) {
    		user.setEmail(userToUpdate.getEmail());
    	}
    	if(userToUpdate.getUsername() != null) {
    		poolUserService.updatePoolUser(username, userToUpdate.getUsername());
    		user.setUsername(userToUpdate.getUsername());
    	}
    	if(userToUpdate.getRole() != null) {
    		user.setRole(userToUpdate.getRole());
    	}
    	if(userToUpdate.getPassword() != null) {
    		user.setPassword(userToUpdate.getPassword());
    	}
        userRepository.save(user);
    	return user;
    }
    
    public User updatePassword(String username, String password) {
    	if(!userExists(username)) {
    		throw new UserNotFoundException("We were unable to find a user for: ", username);
    	}
    	Optional<User> userToUpdate = userRepository.findByUsername(username);
    	User user = userToUpdate.get();
    	user.setPassword(password);
    	userRepository.save(user);
    	return user;
    }
    
    public void setUserAmount(User user) {
    	List<PoolUser> details = user.getPoolDetails();
    	details.stream().forEach(detail -> {
			try {
				Pool pool = poolService.findPoolWithDetails(detail.getPoolId());
	    		double percentage = detail.getPercentage() * .01;
	    		double userAmount = pool.getPoolTotal() * percentage;
	    		detail.setPool(pool);
	    		detail.setUserAmount(userAmount);
	    		Thread.sleep(1000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
    	});
    }
    
    public List<User> getUsers() {
    	List<User> users = userRepository.findAll();
    	users.stream().forEach(user -> {
    		user.setPoolDetails(poolUserRepository.findByUsername(user.getUsername()));
//    		user.getPoolDetails().stream().forEach(detail -> {
//    			if(!poolRepository.findById(detail.getPoolId()).isEmpty()) {
//    				setUserAmount(user);
//    			}
//    		});
    	});
    	return users;
    }
    
    public void insertPoolDetail(User user) {
    	List<PoolUser> details = user.getPoolDetails();
    	details.stream().forEach(detail -> {
    		detail.setUsername(user.getUsername());
    	});
    	poolUserRepository.saveAll(details);
    }
    
    public void deletePoolDetail(User user) {
    	User userToDelete = getUser(user.getUsername());
    	List<PoolUser> details = userToDelete.getPoolDetails();
    	poolUserRepository.deleteAll(details);
    }
    
    public void updatePoolDetail(User user) {
    	User userToUpdate = getUser(user.getUsername());
    	List<PoolUser> details = userToUpdate.getPoolDetails();
    	List<PoolUser> detailsToUpdate = user.getPoolDetails();
    	details.stream().forEach(detail -> {
    		detailsToUpdate.stream().forEach(detailToUpdate -> {
    			if (detailToUpdate.getPoolId().equals(detail.getPoolId())) {
    				detail.setPercentage(detailToUpdate.getPercentage());
    				detail.setUsername(user.getUsername());
    				poolUserRepository.save(detail);
    			}
    		});
    	});
    }
    
}