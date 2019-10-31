package com.karsanov.simplegames.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karsanov.simplegames.model.User;
import com.karsanov.simplegames.service.users.UserPoolService;
import com.karsanov.simplegames.service.users.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserPoolService userPoolService;
	
	@Autowired
	private UserService userService;
 
	@RequestMapping("/")
	public String welcome(Model model) {
		
		//Смотрим, кто в данный момент залогинен.
		String currentUser = "Anonimous";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    currentUser =  currentUserName;
		}
		
		//Если Аноним, то даём на выбор свободные учётные записи
		if (currentUser.equals("Anonimous")) {
			//От общего множества пользователей отнимаем множество залогиненных
			Set<User> unloggedUsers = userService.getAllUsers();
			unloggedUsers.removeAll(userPoolService.getLoggedUsers());
			
			//Подставляем множество пользователей во вью
			model.addAttribute("users", unloggedUsers);
			return "home";
		}
		//Если не Аноним, то переходит в игры
		else {
			return "redirect:/games";
		}
	}
}
