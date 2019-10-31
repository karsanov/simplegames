package com.karsanov.simplegames.service.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.karsanov.simplegames.model.games.blackjack.BlackJackBasicDriver;
import com.karsanov.simplegames.model.games.blackjack.BlackJackBasicGameState;

@Component
public class BlackJackServiceImpl implements BlackJackService {
	
	@Autowired
	BlackJackBasicDriver blackJackBasicDriver;

	@Override
	public BlackJackBasicGameState startNewGame() {
		return blackJackBasicDriver.startNewGame();
	}

	@Override
	public BlackJackBasicGameState getGameState() {
		return blackJackBasicDriver.getGameState();
	}

	@Override
	public BlackJackBasicGameState playerHits() {
		return blackJackBasicDriver.playerHits();
	}

	@Override
	public BlackJackBasicGameState playerStands() {
		return blackJackBasicDriver.playerStands();
	}

}
