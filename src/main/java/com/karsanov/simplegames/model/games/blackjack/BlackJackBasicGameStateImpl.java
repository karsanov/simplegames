package com.karsanov.simplegames.model.games.blackjack;

import java.util.Collection;

import com.karsanov.simplegames.model.games.carddeck.Card;

public class BlackJackBasicGameStateImpl implements BlackJackBasicGameState {
	
	private Collection<Card> dealersHand;
	
	private int dealersScores;
	
	private Collection<Card> palyersHand;
	
	private int playersScores;
	
	private boolean gameFinished;
	
	private boolean playerWins;
	
	private boolean draw;
	

	public BlackJackBasicGameStateImpl() {}

	public BlackJackBasicGameStateImpl(
			Collection<Card> dealersHand, int dealersScores, 
			Collection<Card> palyersHand, int playersScores, 
			boolean gameFinished, boolean playerWins, boolean draw) {

		this.dealersHand = dealersHand;
		this.dealersScores = dealersScores;
		this.palyersHand = palyersHand;
		this.playersScores = playersScores;
		this.gameFinished = gameFinished;
		this.playerWins = playerWins;
		this.draw = draw;
	}

	@Override
	public Collection<Card> getDealersHand() {
		return dealersHand;
	}

	//Сеттеры доступны только в пакете, т.е. драйверам Блэкдека
	//Остальные могут только читать.
	void setDealersHand(Collection<Card> dealersCards) {
		this.dealersHand = dealersCards;
	}

	@Override
	public int getDealersScores() {
		return dealersScores;
	}

	void setDealersScores(int dealersScores) {
		this.dealersScores = dealersScores;
	}

	@Override
	public Collection<Card> getPlayersHand() {
		return palyersHand;
	}

	void setPalyersHand(Collection<Card> palyersCards) {
		this.palyersHand = palyersCards;
	}

	@Override
	public int getPlayersScores() {
		return playersScores;
	}

	void setPlayersScores(int playersScores) {
		this.playersScores = playersScores;
	}

	@Override
	public boolean isGameFinished() {
		return gameFinished;
	}

	void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	@Override
	public boolean isPlayerWins() {
		return playerWins;
	}

	void setPlayerWins(boolean playerWins) {
		this.playerWins = playerWins;
	}

	@Override
	public boolean isDraw() {
		return draw;
	}

	void setDraw(boolean draw) {
		this.draw = draw;
	}
}
