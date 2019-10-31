package com.karsanov.simplegames.service.users;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.karsanov.simplegames.model.User;

@Service
public class UserPoolServiceImpl implements UserPoolService {
	
	private Map<String, User> usersPool = new HashMap<>();

	@Override
	public Set<User> getLoggedUsers() {
		Set<User> users = new HashSet<>();
		for (User user : usersPool.values()) users.add(user);
		return users;
	}

	@Override
	public boolean isLogged(String name) {
		return usersPool.containsKey(name);
	}

	@Override
	public void addUserToPool(User user) {
		usersPool.put(user.getName(), user);
	}
	
	@Override
	public void deleteUserFromPool(String name) {
		usersPool.remove(name);
	}
	
	@Override
	public User findUserByName(String name) {
		return usersPool.get(name);
	}

}
