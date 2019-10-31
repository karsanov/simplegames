package com.karsanov.simplegames.model.games.carddeck;

import java.util.Collection;
import java.util.List;

public interface SimpleCardDeck {
	
	/**
	 * Карта добавляется в колоду на её вершину. После добавления
	 * может понадобиться перетаcовать shuffle() колоду.
	 * @param card
	 */
	public void putCard(Card card);
	
	/**
	 * Все карты добавляются на вершину колоды. После добавления
	 * может понадобиться перетаcовать shuffle() колоду.
	 * @param cardsList
	 */
	public void putCardsList(Collection<Card> cardsList);
	
	/**
	 * Вынуть карту из вершины колоды.
	 * @return
	 * @throws EmptyDeckException 
	 */
	public Card drawCard() throws EmptyDeckException;
	
	/**
	 * Вынуть из вершиины колоды указанное в amountCards количество карт.
	 * Если карт не хватает, то вынуть всё, что получится.
	 * Если колода пуста, возвращает пустой список;
	 * @param amountCards -- количество карт, которые нужно вынуть
	 * из колоды.
	 * @return Список карт, либо пустой список.
	 */
	public List<Card> drawUpToAmountCards(int amountCards);
	
	/**
	 * Получить список всех оставшихся карт.
	 * @return Список оставшихся карт или пустой список.
	 */
	public List<Card> getAllRemainingCards();
	
	public void removeCard(Card card);
	
	public void removeCards(Collection<Card> cards);
	
	public void removeJockers();
	
	public void empty();
	
	public boolean isEmpty();
	
	public int countCards();
	
	public void shuffle();
}
