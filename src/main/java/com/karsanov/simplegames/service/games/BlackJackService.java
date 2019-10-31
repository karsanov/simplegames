package com.karsanov.simplegames.service.games;

import org.springframework.stereotype.Service;

import com.karsanov.simplegames.model.games.blackjack.BlackJackBasicGameState;

@Service
public interface BlackJackService {
	
	public BlackJackBasicGameState startNewGame();

	public BlackJackBasicGameState getGameState();

	public BlackJackBasicGameState playerHits();

	public BlackJackBasicGameState playerStands();
}
