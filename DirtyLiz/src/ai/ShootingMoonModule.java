package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class ShootingMoonModule implements Module {

	private CardTracker tracker;

	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer, Player[] players, MaxFourInt currentPlayer) {
		if(!shouldAttemptToShoot(hand, players, currentPlayer)) {
			return null;
		}
		
		Card cardToPlay = null;
		if (playedCards[leadPlayer.getValue()] == null) {
			return playHighestCard(hand);
		}

		char suitPlayed = playedCards[leadPlayer.getValue()].getSuit();

		cardToPlay = checkForSameSuit(suitPlayed, hand);
		if (cardToPlay != null) {
			return cardToPlay;
		}
		
		//cant follow suit so play low card
		return playLowestCard(hand);

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

	public Card playLowestCard(List<Card> hand) {
		int lowestValue = 15;
		Card lowestValueCard = null;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			int value = card.getValue();
			if (value < lowestValue) {
				lowestValue = value;
				lowestValueCard = card;
			}
		}
		return lowestValueCard;
	}
	
	private boolean shouldAttemptToShoot(List<Card> hand, Player[] players, MaxFourInt currentPlayer) {
		if (isAllOtherPlayerScoresZero(players, currentPlayer)) {
			if (players[currentPlayer.getValue()].getScoreForCurrentHand() > 20) {
				return true;
			}
		}
		return false;
	}

	private boolean isAllOtherPlayerScoresZero(Player[] players, MaxFourInt currentPlayer) {
		for (int i = 0; i < players.length; i++) {
			if (!players[i].equals(currentPlayer.getValue())) {
				if (players[i].getScoreForCurrentHand() > 0) {
					return false;
				}
			}
		}
		return true;
	}
}