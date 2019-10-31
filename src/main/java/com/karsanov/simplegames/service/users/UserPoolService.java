package com.karsanov.simplegames.service.users;

import com.karsanov.simplegames.model.User;

import java.util.Set;

public interface UserPoolService {

	public Set<User> getLoggedUsers();
	
	public boolean isLogged(String name);
	
	public void addUserToPool(User user);
	
	public void deleteUserFromPool(String name);
	
	public User findUserByName(String name);
}
