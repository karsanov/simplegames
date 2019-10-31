package com.karsanov.simplegames.service.users;

import java.util.Set;

import com.karsanov.simplegames.model.User;

public interface UserService {

    User getUser(String login);

    Set<User> getAllUsers();
}