package com.karsanov.simplegames.model.games.carddeck;

import java.util.*;

public class SimpleCardDeckImpl implements SimpleCardDeck {
		
	public static final int DEFAULT_DECK_SIZE = 54;

	private LinkedList<Card> deck;
	private int index = -1; //Индекс меньше нуля, когда в колоде нет ни одной карты
	
	/**
	 * Создаёт колоду для 54 карт, но не заполняет её
	 */
	public SimpleCardDeckImpl() {
		deck = new LinkedList<>();
	}
	
	/**
	 * Создаёт колоду соответствующего размера и заполняет
	 * разными картами.
	 * @param classicDeckSet.SET52 - нет джокеров, .SET53, .SET54 - два джокера,
	 * 						 SET36 - классическая русская колода.
	 */
	public SimpleCardDeckImpl(ClassicSet classicDeckSet) {
		deck = new LinkedList<>();
		switch(classicDeckSet) {
		case SET36:
			fillDeckWith36UniqueCards();
			index = 35;
			break;
		case SET52:
			fillDeckWith54UniqueCards();
			removeJockerFromDeck(true);
			index = 51;
			break;
		case SET53:
			fillDeckWith54UniqueCards();
			removeJockerFromDeck(false);
			index = 52;
			break;
		case SET54:
		default:
			fillDeckWith54UniqueCards();
			index = 53;
			break;			
		}
	}
	
	private void fillDeckWith36UniqueCards() {
		deck.addAll(Card.getRussian36CardSet());
	}
	
	private void fillDeckWith54UniqueCards() {
		deck.addAll(Card.getFull54CardSet());
	}
	
	/**
	 * Удаляет джокеров из колоды.
	 * @param isRemoveBoth -- если true  -- удалит двух джокеров,
	 * если false -- удалить одного.
	 * 
	 * Метод нужно перегрузить, чтобы различать цветного и 
	 * чёрно-белого джокеров.
	 */
	private void removeJockerFromDeck(boolean isRemoveBoth) {
		if (isRemoveBoth) {
			deck.remove(Card.JOCKER1);
			deck.remove(Card.JOCKER2);
		} else {
			//Если нужно удалить одного джокера, то 
			//сперва пробуем удалить второй, если его нет, то первый.
			if (deck.contains(Card.JOCKER2)) deck.remove(Card.JOCKER2);
			else deck.remove(Card.JOCKER1);
		}
	}
	
	//Методы интерфейса SimpleCardDeck
	//////////////////////////////////////////////////////
	@Override
	public void putCard(Card card) {
		if (card == null) throw new NullPointerException();
		
		deck.add(card);
		index++;
	}

	@Override
	public void putCardsList(Collection<Card> cardsList) {
		if (cardsList == null) throw new NullPointerException();

		deck.addAll(cardsList);
		if (deck.removeIf(Objects::isNull)) throw new NullPointerException("В колоду помещён 1 или более null.");
		index += cardsList.size();
	}

	@Override
	public Card drawCard() throws EmptyDeckException {
		if (index == -1) throw new EmptyDeckException();
		
		Card topCard = deck.remove(index);
		index--;
		return topCard;
	}

	@Override
	public List<Card> drawUpToAmountCards(int amountCards) {
		if (amountCards < 0) 
			throw new IllegalArgumentException("Количество карт не может быть отрицательным");

		if (amountCards >= countCards()) return getAllRemainingCards();
		else {
			List<Card> drawnCards = new ArrayList<>();
			for (int i = 0; i < amountCards; i++) {
				try {
					drawnCards.add(drawCard());
				} catch (EmptyDeckException e) {
					e.printStackTrace();
				}
			}
			index -= amountCards;
			return drawnCards;
		}
	}

	@Override
	public List<Card> getAllRemainingCards() {
		List<Card> remainingCards = new ArrayList<>(deck);
		deck.clear();
		index = -1;
		return remainingCards;
	}
	
	@Override
	public void removeCard(Card card) {
		if (card == null) throw new NullPointerException("Попытка удалить карту из колоды, но передан null");
		if (deck.remove(card)) index--;
	}
	
	@Override
	public void removeCards(Collection<Card> cards) {
		if (cards == null) throw new NullPointerException("Попытка удалить список карт из колоды, но передан null");
		for (Card card : cards) removeCard(card);
	}
	
	@Override
	public void removeJockers() {
		deck.remove(Card.JOCKER1);
		deck.remove(Card.JOCKER2);
		index--;
		index--;
	}
	
	@Override
	public void empty() {
		deck.clear();
		index = -1;
	}
	
	@Override
	public boolean isEmpty() {
		if (index == -1) return true;
		else return false;
	}
	
	@Override
	public int countCards() {
		return index+1;
	}

	@Override
	public void shuffle() {
		if (index == -1 || index == 0) return; //Одна или ноль карт -- не тасуем
		
		//Количество перетасовок зависит от размера колоды
		Random rand = new Random();
		for (int i = 0; i < index; i++) {
			int index1 = rand.nextInt(index+1);
			int index2 = rand.nextInt(index+1);
			
			if (index1 == index2) continue;
			
			Card fstCard = deck.get(index1);
			Card sndCard = deck.get(index2);
			deck.remove(index1);
			deck.add(index1, sndCard);
			deck.remove(index2);
			deck.add(index2, fstCard);
		}
	}
}
