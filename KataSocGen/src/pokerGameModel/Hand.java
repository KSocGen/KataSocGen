package pokerGameModel;

import java.util.Arrays;

public class Hand {
	public enum handRank {
		HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
	}

	private Card[] hand;
	private String name;

	public Hand() {
		hand = null;
		name = "";
	}

	public Hand(String handString, String name) {
		System.out.println(" Hand Constructor");
		String[] cards = handString.split(" ");
		hand = new Card[cards.length];
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new Card(cards[i]);
		}
		Arrays.sort(hand);
		this.name = name;
		System.out.println(" Name " + name);
	}

	public Card[] getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}
}
