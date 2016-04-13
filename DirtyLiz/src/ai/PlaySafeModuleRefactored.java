package ai;

import java.util.List;
import commmon.Card;
import commmon.MaxFourInt;

/** Same as Play Safe Module with all logic regarding Hearts and Spades removed **/

public class PlaySafeModuleRefactored implements Module{

	private CardTracker tracker;

	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {

		Card cardToPlay = null;
		if (playedCards[leadPlayer.getValue()] == null) {
			cardToPlay = playLowestCard(hand);
			return cardToPlay;
		} 

		char suitPlayed = playedCards[leadPlayer.getValue()].getSuit();

		cardToPlay = checkForSameSuitToPlay(suitPlayed, hand, playedCards, leadPlayer);
		if (cardToPlay != null) {
			return cardToPlay;
		}
		
		cardToPlay = playLowestSameSuitCard(suitPlayed, hand);
		//return cardToPlay = playHighestValueCardIfNotLead(hand);
		
		return cardToPlay;
	}

	public Card checkForSameSuitToPlay(char suit, List<Card> hand, Card[] playedCards, MaxFourInt leadPlayer) {
		Card returnCard = null;
		int valueToBeat = playedCards[leadPlayer.getValue()].getValue();
		for (int i = 0; i < playedCards.length; i++) {
			//Check if anyone has played a higher card than the lead.
			if (playedCards[i] != null && playedCards[i].getSuit() == suit && playedCards[i].getValue() > valueToBeat) {
				valueToBeat = playedCards[i].getValue();
			}
		}

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == (suit)) {
				int value = card.getValue();
				if (value < valueToBeat) {
					if (returnCard == null) {
						returnCard = card;
					} else {
						if (value > returnCard.getValue()) {
							returnCard = card;
						}
					}
				} 
			}
		}

		return returnCard;
	}
	
	public Card playLowestSameSuitCard(char suit, List<Card> hand){
		Card returnCard = null;
		int valueToBeat=15;
		
		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit() == suit){
				int value = hand.get(i).getValue();
				if(value < valueToBeat){
					valueToBeat = value;
					returnCard = card;
				}
			}
		}
				
				
		return returnCard;
		
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

	public Card playHighestValueCardIfNotLead(List<Card> hand) {
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
