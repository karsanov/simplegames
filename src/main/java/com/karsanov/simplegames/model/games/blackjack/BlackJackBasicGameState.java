package com.karsanov.simplegames.model.games.blackjack;

import java.util.*;

import com.karsanov.simplegames.model.games.carddeck.Card;

public interface BlackJackBasicGameState {

	public Collection<Card> getDealersHand();
	
	public int getDealersScores();
	
	public Collection<Card> getPlayersHand();
	
	public int getPlayersScores();
	
	public boolean isGameFinished();
	
	public boolean isPlayerWins();
	
	public boolean isDraw();
}
