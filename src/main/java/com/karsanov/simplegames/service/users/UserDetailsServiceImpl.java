package com.karsanov.simplegames.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karsanov.simplegames.model.User;
import com.karsanov.simplegames.model.UserRoleEnum;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserPoolService userPoolService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getUser(username);

		// Смотрим, залогинен ли пользователь.
		// Если да, то сбрасыаем пароль, чтобы не логинился снова.
		boolean isLoggedUser = false;
		if (userPoolService.isLogged(username)) {
			user.setPassword("");
			isLoggedUser = true;
		}
		
		//Если пользователь ещё не логинился, то добавляем его в пул
		if (!isLoggedUser) userPoolService.addUserToPool(user);

		//Зполняем userDetails
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
				user.getPassword(), roles);

		return userDetails;
	}

}