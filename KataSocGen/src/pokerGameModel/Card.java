package pokerGameModel;

public class Card implements Comparable<Card> {

	private int rank;
	private int value;
	private char suit;
	public static final String VALUES = "23456789TJQKA";
	public static final String SUITS = "CDHS";

	public Card(String str) {
		rank = ranking(str);
		value = rank / SUITS.length();
		suit = SUITS.charAt(rank % SUITS.length());
	}

	public int getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}

	public char getSuit() {
		return suit;
	}

	public int ranking(String str) {
		char value = str.charAt(0);
		char suit = str.charAt(1);
		for (int i = 0; i < VALUES.length(); i++) {
			if (VALUES.charAt(i) == value) {
				for (int j = 0; j < SUITS.length(); j++) {
					if (SUITS.charAt(j) == suit)
						return (i + 2) * SUITS.length() + j;
				}
			}
		}

		return -1;
	}

	@Override
	public int compareTo(Card anotherCard) {
		if (this.getValue() < anotherCard.getValue())
			return -1;
		else if (this.getValue() > anotherCard.getValue())
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "[" + value + suit + "]";
	}

}