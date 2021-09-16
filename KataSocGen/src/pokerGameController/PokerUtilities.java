package pokerGameController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import pokerGameModel.Card;
import pokerGameModel.Hand;

class PokerUtilities {

	boolean isPair(Card[] hand) {
		int card1 = hand[0].getValue();
		int card2 = hand[1].getValue();
		int card3 = hand[2].getValue();
		int card4 = hand[3].getValue();
		int card5 = hand[4].getValue();

		return ((card1 == card2 && card2 != card3) || (card2 == card3 && card3 != card4 && card2 != card1)
				|| (card3 == card4 && card4 != card5 && card3 != card2) || (card4 == card5 && card4 != card3));
	}

	boolean isTwoPair(Card[] hand) {
		int card1 = hand[0].getValue();
		int card2 = hand[1].getValue();
		int card3 = hand[2].getValue();
		int card4 = hand[3].getValue();
		int card5 = hand[4].getValue();

		return ((card1 == card2 && card3 == card4 && card2 != card3 && card4 != card5)
				|| (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5));
	}

	boolean isThreeOfAKind(Card[] hand) {
		int card1 = hand[0].getValue();
		int card2 = hand[1].getValue();
		int card3 = hand[2].getValue();
		int card4 = hand[3].getValue();
		int card5 = hand[4].getValue();

		return (card1 == card3 || card2 == card4 || card3 == card5);
	}

	boolean isStraight(Card[] hand) {

		return hand[4].getValue() - hand[0].getValue() == 4;
	}

	boolean isFlush(Card[] hand) {

		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i + 1].getSuit() != hand[i].getSuit())
				return false;
		}

		return true;
	}

	boolean isFullHouse(Card[] hand) {
		int card1 = hand[0].getValue();
		int card2 = hand[1].getValue();
		int card3 = hand[2].getValue();
		int card4 = hand[3].getValue();
		int card5 = hand[4].getValue();

		return ((card1 == card2 && card2 != card3 && card3 == card5)
				|| (card1 == card3 && card3 != card4 && card4 == card5));
	}

	boolean isFourOfAKind(Card[] hand) {

		return (hand[0].getValue() == hand[3].getValue() || hand[1].getValue() == hand[4].getValue());
	}

	boolean isStraightFlush(Card[] hand) {

		return isStraight(hand) && isFlush(hand);
	}

	int compareHighCard(Hand hand1, Hand hand2) {
		Card[] hand1Cards = hand1.getHand();
		Card[] hand2Cards = hand2.getHand();
		return hand1Cards[hand1Cards.length - 1].compareTo(hand2Cards[hand2Cards.length - 1]);
	}

	int compareRecursiveHighCard(Hand hand1, Hand hand2) {
		Card[] hand1Cards = hand1.getHand();
		Card[] hand2Cards = hand2.getHand();
		int current = hand1Cards.length - 1;
		int cmp = 0;
		while (current >= 0) {
			cmp = hand1Cards[current].compareTo(hand2Cards[current]);
			if (cmp != 0)
				break;
			current--;
		}
		return cmp;
	}

	Card compareFourOfAKind(Hand tempHand) {
		Card[] tempCards = tempHand.getHand();
		if (tempCards[0] == tempCards[3])
			return tempCards[0];
		else if (tempCards[1] == tempCards[4])
			return tempCards[1];
		return null;
	}

	Card compareThreeOfAKind(Hand tempHand) {
		Card[] tempCards = tempHand.getHand();
		if (tempCards[0] == tempCards[2])
			return tempCards[0];
		else if (tempCards[1] == tempCards[3])
			return tempCards[1];
		else if (tempCards[2] == tempCards[4])
			return tempCards[2];
		return null;
	}

	int compareTwoPair(Hand hand1, Hand hand2) {
		Card[] hand1Cards = hand1.getHand();
		int card1 = hand1Cards[0].getValue();
		int card2 = hand1Cards[1].getValue();
		int card3 = hand1Cards[2].getValue();
		int card4 = hand1Cards[3].getValue();
		int card5 = hand1Cards[4].getValue();
		Card hand1Bigger = null;
		Card hand1Smaller = null;
		Card hand1Remain = null;
		if (card1 == card2 && card3 == card4 && card2 != card3 && card4 != card5) {
			if (card1 > card3) {
				hand1Bigger = hand1Cards[0];
				hand1Smaller = hand1Cards[2];
				hand1Remain = hand1Cards[4];
			} else {
				hand1Bigger = hand1Cards[2];
				hand1Smaller = hand1Cards[0];
				hand1Remain = hand1Cards[4];
			}
		}
		if (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5) {
			if (card2 > card4) {
				hand1Bigger = hand1Cards[1];
				hand1Smaller = hand1Cards[3];
				hand1Remain = hand1Cards[0];
			} else {
				hand1Bigger = hand1Cards[3];
				hand1Smaller = hand1Cards[1];
				hand1Remain = hand1Cards[0];
			}
		}

		Card[] hand2Cards = hand1.getHand();
		int card11 = hand2Cards[0].getValue();
		int card22 = hand2Cards[1].getValue();
		int card33 = hand2Cards[2].getValue();
		int card44 = hand2Cards[3].getValue();
		int card55 = hand2Cards[4].getValue();
		Card hand2Bigger = null;
		Card hand2Smaller = null;
		Card hand2Remain = null;
		if (card11 == card22 && card33 == card44 && card22 != card33 && card44 != card55) {
			if (card11 > card33) {
				hand2Bigger = hand2Cards[0];
				hand2Smaller = hand2Cards[2];
				hand2Remain = hand2Cards[4];
			} else {
				hand2Bigger = hand2Cards[2];
				hand2Smaller = hand2Cards[0];
				hand2Remain = hand2Cards[4];
			}
		}
		if (card11 != card22 && card22 == card33 && card33 != card44 && card44 == card55) {
			if (card22 > card44) {
				hand2Bigger = hand2Cards[1];
				hand2Smaller = hand2Cards[3];
				hand2Remain = hand2Cards[0];
			} else {
				hand2Bigger = hand2Cards[3];
				hand2Smaller = hand2Cards[1];
				hand2Remain = hand2Cards[0];
			}
		}

		if (hand1Bigger.compareTo(hand2Bigger) != 0)
			return hand1Bigger.compareTo(hand2Bigger);
		else if (hand1Smaller.compareTo(hand2Smaller) != 0)
			return hand1Smaller.compareTo(hand2Smaller);
		else
			return hand1Remain.compareTo(hand2Remain);
	}

	int comparePair(Hand hand1, Hand hand2) {
		Card[] hand1Cards = hand1.getHand();
		Card[] hand2Cards = hand2.getHand();
		Set<Card> hand1Set = new HashSet<Card>();
		Set<Card> hand2Set = new HashSet<Card>();

		Card hand1PairCard = null;
		Card hand2PairCard = null;
		for (int i = 0; i < hand1Cards.length; i++) {
			if (!hand1Set.contains(hand1Cards[i]))
				hand1Set.add(hand1Cards[i]);
			else
				hand1PairCard = hand1Cards[i];
		}
		for (int i = 0; i < hand2Cards.length; i++) {
			if (!hand2Set.contains(hand2Cards[i]))
				hand2Set.add(hand2Cards[i]);
			else
				hand2PairCard = hand2Cards[i];
		}

		if (hand1PairCard.compareTo(hand2PairCard) != 0)
			return hand1PairCard.compareTo(hand2PairCard);
		else {
			while (!hand1Set.isEmpty() && !hand2Set.isEmpty()) {
				Card tempHand1Max = Collections.max(hand1Set);
				Card tempHand2Max = Collections.max(hand2Set);
				if (tempHand1Max.compareTo(tempHand2Max) != 0)
					return tempHand1Max.compareTo(tempHand2Max);
				hand1Set.remove(tempHand1Max);
				hand2Set.remove(tempHand2Max);
			}
		}
		return -10000;
	}

}
