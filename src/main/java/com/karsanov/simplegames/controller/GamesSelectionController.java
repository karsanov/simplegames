package com.karsanov.simplegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karsanov.simplegames.model.User;
import com.karsanov.simplegames.service.users.UserPoolService;

@Controller
public class GamesSelectionController {
	
	@Autowired
	private UserPoolService userPoolService;
	
	@RequestMapping("/games")
	public String gamesMenu(Model model) {

		// Проверяем, залогинен ли пользователь.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			try {// Получаем пользователя из пула. Если null, возвращаемся на главную
				User currentUser = userPoolService.findUserByName(currentUserName);
				model.addAttribute("user", currentUser);
			} catch (NullPointerException e) {
				System.err.println("Пользователь есть в системе, но не зарегистрирован в пуле.");
				return "redirect:/logout";
			}

		} else {//Если пользователь не залогинен, то возвращаемся на главную
			return "redirect:/";
		}

		return "games";
	}
}
