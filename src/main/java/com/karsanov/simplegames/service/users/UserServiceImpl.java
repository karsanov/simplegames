package com.karsanov.simplegames.service.users;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karsanov.simplegames.model.User;
import com.karsanov.simplegames.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

    @Override
    public User getUser(String login) {
        User user = userRepository.getUser(login);
        return user;
    }

	@Override
	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<>();
		users.addAll(userRepository.getAllUsers());
		return users;
	}

}