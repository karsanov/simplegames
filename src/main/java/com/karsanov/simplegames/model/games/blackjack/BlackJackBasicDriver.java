package com.karsanov.simplegames.model.games.blackjack;

public interface BlackJackBasicDriver {
	
	public BlackJackBasicGameState startNewGame();
	
	public BlackJackBasicGameState getGameState();
	
	public BlackJackBasicGameState playerHits();
	
	public BlackJackBasicGameState playerStands();
}
