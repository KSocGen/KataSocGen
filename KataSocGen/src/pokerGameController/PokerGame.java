package pokerGameController;

import pokerGameModel.Card;
import pokerGameModel.Hand;

public class PokerGame {

	PokerUtilities pokerUtilities = new PokerUtilities();

	public Hand.handRank getHandRank(Hand hand) {
		Card[] tempHand = hand.getHand();
		if (pokerUtilities.isStraightFlush(tempHand))
			return Hand.handRank.STRAIGHTFLUSH;
		else if (pokerUtilities.isFourOfAKind(tempHand))
			return Hand.handRank.FOUROFAKIND;
		else if (pokerUtilities.isFullHouse(tempHand))
			return Hand.handRank.FULLHOUSE;
		else if (pokerUtilities.isFlush(tempHand))
			return Hand.handRank.FLUSH;
		else if (pokerUtilities.isStraight(tempHand))
			return Hand.handRank.STRAIGHT;
		else if (pokerUtilities.isThreeOfAKind(tempHand))
			return Hand.handRank.THREEOFAKIND;
		else if (pokerUtilities.isTwoPair(tempHand))
			return Hand.handRank.TWOPAIR;
		else if (pokerUtilities.isPair(tempHand))
			return Hand.handRank.PAIR;
		else
			return Hand.handRank.HIGHCARD;
	}

	public String getWinner(Hand hand1, Hand hand2) {
		Hand.handRank hand1Rank = getHandRank(hand1);
		Hand.handRank hand2Rank = getHandRank(hand2);

		int cmp = hand1Rank.compareTo(hand2Rank);
		if (cmp > 0)
			return hand1.getName();
		else if (cmp < 0)
			return hand2.getName();
		else {
			switch (hand1Rank) {
			case STRAIGHTFLUSH:
				cmp = pokerUtilities.compareHighCard(hand1, hand2);
				break;
			case FOUROFAKIND:
				cmp = pokerUtilities.compareFourOfAKind(hand1).compareTo(pokerUtilities.compareFourOfAKind(hand2));
				break;
			case FULLHOUSE:
				cmp = pokerUtilities.compareThreeOfAKind(hand1).compareTo(pokerUtilities.compareThreeOfAKind(hand2));
				break;
			case FLUSH:
				cmp = pokerUtilities.compareRecursiveHighCard(hand1, hand2);
				break;
			case STRAIGHT:
				cmp = pokerUtilities.compareHighCard(hand1, hand2);
				break;
			case THREEOFAKIND:
				cmp = pokerUtilities.compareThreeOfAKind(hand1).compareTo(pokerUtilities.compareThreeOfAKind(hand2));
				break;
			case TWOPAIR:
				cmp = pokerUtilities.compareTwoPair(hand1, hand2);
				break;
			case PAIR:
				break;
			case HIGHCARD:
				cmp = pokerUtilities.compareRecursiveHighCard(hand1, hand2);
				break;
			}
		}

		return cmp > 0 ? hand1.getName() : cmp < 0 ? hand2.getName() : "Tie.";
	}
}
