package com.karsanov.simplegames.controller.games;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karsanov.simplegames.model.User;
import com.karsanov.simplegames.model.games.blackjack.BlackJackBasicGameState;
import com.karsanov.simplegames.model.games.carddeck.Card;
import com.karsanov.simplegames.service.games.BlackJackService;
import com.karsanov.simplegames.service.users.UserPoolService;

@Controller
public class BlackJackController {

	@Autowired
	private BlackJackService blackJackService;

	@Autowired
	private UserPoolService userPoolService;

	@RequestMapping(value = "/games/blackjack")
	public String blackJack(Model model, @RequestParam(required = false) String action) {

		// Проверяем, залогинен ли пользователь.
		// Если да, то выводим его картинку
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

		// Обрабатываем запросу пользователя
		BlackJackBasicGameState gameState = blackJackService.getGameState();

		if (action == null) {
			sendGameStateDataToPage(model, gameState);
			return "/games/blackjack";

		} else if (action.equals("newgame")) {
			blackJackService.startNewGame();

		} else if (action.equals("hit")) {
			blackJackService.playerHits();

		} else if (action.equals("stand")) {
			blackJackService.playerStands();
		}

		return "redirect:/games/blackjack";
	}

	private void sendGameStateDataToPage(Model model, BlackJackBasicGameState gameState) {
		Collection<Card> dealersHand = gameState.getDealersHand();
		Collection<Card> playersHand = gameState.getPlayersHand();
		int dealersScores = gameState.getDealersScores();
		int playersScores = gameState.getPlayersScores();

		model.addAttribute("dealersHand", dealersHand);
		model.addAttribute("playersHand", playersHand);
		model.addAttribute("dealersScores", dealersScores);
		model.addAttribute("playersScores", playersScores);

		sendGameResultDataToPage(model, gameState);
	}

	private void sendGameResultDataToPage(Model model, BlackJackBasicGameState gameState) {
		boolean isGameFinished = gameState.isGameFinished();
		model.addAttribute("isGameFinished", isGameFinished);
		if (isGameFinished) {
			String result = "";
			if (gameState.isDraw())
				result = "Ничья";
			else if (gameState.isPlayerWins())
				result = "Вы выиграли";
			else
				result = "Вы проиграли";
			model.addAttribute("gameResult", result);
		}

	}
}