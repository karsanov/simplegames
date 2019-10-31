package com.karsanov.simplegames.model.games.blackjack;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.karsanov.simplegames.model.games.carddeck.Card;
import com.karsanov.simplegames.model.games.carddeck.EmptyDeckException;
import com.karsanov.simplegames.model.games.carddeck.SimpleCardDeck;
import com.karsanov.simplegames.model.games.carddeck.SimpleCardDeckImpl;

@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES)
@Component
public class BlackJackBasicDriverImpl implements BlackJackBasicDriver {

	private BlackJackBasicGameStateImpl gameState;
	
	private SimpleCardDeck deck;
	
	private class HandState {
		private Collection<Card> hand = new LinkedList<>(); //Все карты в порядке добавления
		private Map<Card, Integer> notAceCardsToValues = new TreeMap<>(); // Не тузы и соответствующие очки
		private int amountOfAces; // Количество тузов
		private int scores;
	}

	private HandState dealer;
	private HandState human;

	private boolean gameFinished;
	private boolean playerWins;
	private boolean draw;

	public static void main (String[] args) {
		BlackJackBasicDriverImpl g = new BlackJackBasicDriverImpl();
		
		g.startNewGame();
		
		g.playerHits();
		g.playerHits();
		g.playerHits();
		
		g.playerStands();
		
		for (Card c : g.getGameState().getDealersHand()) {
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println(g.dealer.scores);
		
		for (Card c : g.getGameState().getPlayersHand()) {
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println(g.human.scores);
		
		if (g.getGameState().isDraw()) System.out.println("Ничья");
		else if (g.getGameState().isPlayerWins()) System.out.println("Вы выиграли");
		else System.out.println("Вы проиграли");
	}
	
	public BlackJackBasicDriverImpl() {
		gameState = new BlackJackBasicGameStateImpl();
		deck = new SimpleCardDeckImpl();
		dealer = new HandState();
		human = new HandState();
		
		startNewGame();
	}
	
	private void nullifyGameState() {	
		dealer.hand.clear();
		dealer.notAceCardsToValues.clear();
		dealer.amountOfAces = 0;
		dealer.scores = 0;
		
		human.hand.clear();
		human.notAceCardsToValues.clear();
		human.amountOfAces = 0;
		human.scores = 0;
		
		gameFinished = false;
		playerWins = false;
		draw = false;
	}

	private void playForDealer() throws EmptyDeckException {
		boolean stand = false;
		int scores = 0;
		do {
			Card currentCard = deck.drawCard();
			proceedDrawnCard(currentCard, dealer);
			
			scores = countTotalScores(dealer);
			
			if (scores > 16) stand = true;

		} while (!stand);
		
		dealer.scores = scores;
	}

	private void proceedDrawnCard(Card card, HandState currentPlayer) {
		int cardScores = countCardScores(card);
		boolean isAce = card.getValue().equals("Ace");
			if (isAce) currentPlayer.amountOfAces++;
			else currentPlayer.notAceCardsToValues.put(card, cardScores);
			currentPlayer.hand.add(card);
	}

	private int countCardScores(Card card) {
		if (card.getValue().equals("Ace")) return 11;
		if (card.isFace()) return 10;
		return Integer.parseInt(card.getValue());
	}
	
	
	private int countTotalScores(HandState currentPlayer) {
		int sum = 0;
		
		for (Entry<Card, Integer> e : currentPlayer.notAceCardsToValues.entrySet()) {
			sum += e.getValue();
		}
		
		int aceScores = currentPlayer.amountOfAces * 11;
		//Рассчитываем очки за тузы, 
		//в случае перебоа, туз даёт 1, а не 11.
		for (int i = 0; i < currentPlayer.amountOfAces; i++) {
			if ((sum + aceScores) > 21) aceScores -= 10;
			else break;
		}

		return sum + aceScores;
	}
	
	public void computeGameResults() {
		gameFinished = true;
		
		if (human.scores > 21 && dealer.scores > 21) draw = true;
		else if (human.scores == dealer.scores) draw = true;
		
		else if (human.scores > 21) playerWins = false;
		else if (dealer.scores > 21) playerWins = true;
		
		else if (human.scores > dealer.scores) playerWins = true;
	}

	//Обещанные методы
	@Override
	public BlackJackBasicGameState startNewGame() {
		nullifyGameState();
		deck.empty();
		deck.putCardsList(Card.getFull54CardSet());
		deck.removeJockers();
		deck.shuffle();
		
		try {
			playForDealer();
		} catch (EmptyDeckException e) {}
		
		playerHits();
		playerHits();
		
		return getGameState();
	}
	
	@Override
	public BlackJackBasicGameState getGameState() {
		Collection<Card> dealersHand = new LinkedList<>();
		//Если игра не закончена, то карты на руках дилера 
		//не показываются и в GameState кладутся рубашки крат
		if (!gameFinished) for (int i = 0; i < dealer.hand.size(); i++) dealersHand.add(Card.BACK);
		else dealersHand = dealer.hand;
		
		gameState.setDealersHand(dealersHand);
		gameState.setDealersScores(dealer.scores);
		gameState.setPalyersHand(human.hand);
		gameState.setPlayersScores(human.scores);
		gameState.setGameFinished(gameFinished);
		gameState.setPlayerWins(playerWins);
		gameState.setDraw(draw);
		
		return gameState;
	}

	@Override
	public BlackJackBasicGameState playerHits() {
		if (gameFinished) startNewGame();//Если кто-то захочет продолжить законченную игру, то создаём новую
		
		try {
			
			Card currentCard = deck.drawCard();
			proceedDrawnCard(currentCard, human);
			
			
		} catch (EmptyDeckException e) {
			System.err.println("Достигнут конец колоды. Это нарушает инвариант, "
					+ "\nчто два игрока не могут употребить 52 карты за матч.");
			e.printStackTrace();
			//Прекращаем игру и считаем очки.
			playerStands();
		}

		if (countTotalScores(human) > 21) playerStands();
		
		human.scores = countTotalScores(human);
		
		return getGameState();
	}

	@Override
	public BlackJackBasicGameState playerStands() {
		if (gameFinished) startNewGame();//Если кто-то захочет продолжить законченную игру, то создаём новую
		
		human.scores = countTotalScores(human);
		computeGameResults();
		return getGameState();
	}
}
