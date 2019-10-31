package com.karsanov.simplegames.model.games.carddeck;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public enum Card {

	H2("Hearts", "2", true, false), H3("Hearts", "3", true, false), H4("Hearts", "4", true, false),
	H5("Hearts", "5", true, false), H6("Hearts", "6", true, false), H7("Hearts", "7", true, false),
	H8("Hearts", "8", true, false), H9("Hearts", "9", true, false), H10("Hearts", "10", true, false),
	HJ("Hearts", "Jack", true, true), HQ("Hearts", "Queen", true, true), 
	HK("Hearts", "King", true, true), HA("Hearts", "Ace", true, true),

	D2("Diamonds", "2", true, false), D3("Diamonds", "3", true, false), D4("Diamonds", "4", true, false),
	D5("Diamonds", "5", true, false), D6("Diamonds", "6", true, false), D7("Diamonds", "7", true, false),
	D8("Diamonds", "8", true, false), D9("Diamonds", "9", true, false), D10("Diamonds", "10", true, false),
	DJ("Diamonds", "Jack", true, true), DQ("Diamonds", "Queen", true, true), 
	DK("Diamonds", "King", true, true), DA("Diamonds", "Ace", true, true),

	S2("Spades", "2", false, false), S3("Spades", "3", false, false), S4("Spades", "4", false, false),
	S5("Spades", "5", false, false), S6("Spades", "6", false, false), S7("Spades", "7", false, false),
	S8("Spades", "8", false, false), S9("Spades", "9", false, false), S10("Spades", "10", false, false),
	SJ("Spades", "Jack", false, true), SQ("Spades", "Queen", false, true), 
	SK("Spades", "King", false, true), SA("Spades", "Ace", false, true),

	C2("Clubs", "2", false, false), C3("Clubs", "3", false, false), C4("Clubs", "4", false, false),
	C5("Clubs", "5", false, false), C6("Clubs", "6", false, false), C7("Clubs", "7", false, false),
	C8("Clubs", "8", false, false), C9("Clubs", "9", false, false), C10("Clubs", "10", false, false),
	CJ("Clubs", "Jack", false, true), CQ("Clubs", "Queen", false, true), 
	CK("Clubs", "King", false, true), CA("Clubs", "Ace", false, true),

	JOCKER1("Jocker", "Jocker", false, false), JOCKER2("Jocker", "Jocker", false, false),
	BACK("Unknown", "Unknown", false, true);

	private final String suit;
	private final String value;
	private final boolean isRed;
	private final boolean isFace;

	private Card(String suit, String value, boolean isRed, boolean isFace) {
		this.suit = suit;
		this.value = value;
		this.isRed = isRed;
		this.isFace = isFace;
	}

	public String getSuit() {
		return suit;
	}

	public String getValue() {
		return value;
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isFace() {
		return isFace;
	}

	private static Set<Card> full54CardSet = new TreeSet<>(Arrays.asList(
			H2, H3, H4, H5, H6, H7, H8, H9, H10, HJ, HQ, HK, HA, 
			D2, D3, D4, D5, D6, D7, D8, D9, D10, DJ, DQ, DK, DA, 
			S2, S3, S4, S5, S6, S7, S8, S9, S10, SJ, SQ, SK, SA, 
			C2, C3, C4, C5, C6, C7, C8, C9, C10, CJ, CQ, CK, CA, 
			JOCKER1, JOCKER2));

	public static Collection<Card> getFull54CardSet() {

		return Collections.unmodifiableCollection(full54CardSet);
	}
	
	private static Set<Card> russian36CardSet = 
			new TreeSet<>(Arrays.asList(H6, H7, H8, H9, H10, HJ, HQ, HK, HA, 
					   					D6, D7, D8, D9, D10, DJ, DQ, DK, DA, 
					   					S6, S7, S8, S9, S10, SJ, SQ, SK, SA, 
					   					C6, C7, C8, C9, C10, CJ, CQ, CK, CA));
	
	public static Collection<Card> getRussian36CardSet() {
		
		return Collections.unmodifiableCollection(russian36CardSet);
	}
}
