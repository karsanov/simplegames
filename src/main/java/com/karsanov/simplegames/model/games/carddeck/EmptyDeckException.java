package com.karsanov.simplegames.model.games.carddeck;

public class EmptyDeckException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Попытка извлечь карту из пустой колоды.";
	}
}
