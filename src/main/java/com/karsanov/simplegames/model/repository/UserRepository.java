package com.karsanov.simplegames.model.repository;

import java.util.List;

import com.karsanov.simplegames.model.User;

public interface UserRepository {
	
	public User getUser(int id);
	
	public User getUser(String name);
	
	public List<User> getAllUsers();
}
