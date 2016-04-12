package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class PlayHighModule {

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {

		Card cardToPlay = null;
		if (playedCards[leadPlayer.getValue()] == null) {
			cardToPlay = playHighestCard(hand);
			return cardToPlay;

		}

		char suitPlayed = playedCards[leadPlayer.getValue()].getSuit();

		cardToPlay = checkForSameSuit(suitPlayed, hand);
		if (cardToPlay != null) {
			return cardToPlay;
		}

		if (hand.contains(Card.QUEEN_SPADES)) {
			return Card.QUEEN_SPADES;
		}

		cardToPlay = checkForHeart(hand);
		if (cardToPlay != null) {
			return cardToPlay;
		}

		return cardToPlay = playHighestCard(hand);

	}

	public Card checkForSameSuit(char suit, List<Card> hand) {
		int highestValue = 0;
		Card highestValueCard = null;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == (suit)) {
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					highestValueCard = card;
				}
			}
		}
		return highestValueCard;
	}

	public Card checkForHeart(List<Card> hand) {
		Card findHeart = null;
		int highestValue = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == Card.HEARTS) {
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					findHeart = card;
				}
			}
		}
		return findHeart;
	}

	public Card playHighestCard(List<Card> hand) {
		int highestValue = 0;
		Card highestValueCard = null;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			int value = card.getValue();
			if (value > highestValue) {
				highestValue = value;
				highestValueCard = card;
			}
		}
		return highestValueCard;
	}
}
