package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class HeartsModule implements Module{
	
	private CardTracker tracker;
	
	//access to tracker 
	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}
		
	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;
		
		if(playedCards[leadPlayer.getValue()] == null) {
			return returnCard;
		}
		
		char suitLead = playedCards[leadPlayer.getValue()].getSuit();
		
		if(suitLead == Card.HEARTS){
			returnCard = playHighestHeartIfLost(hand, playedCards, leadPlayer);
			//System.out.println("HEART MODULE PLAYHIGHESTHEARTIFLOST " + returnCard);
		}
		
		if(returnCard == null){
			if(suitLead == Card.HEARTS){
				returnCard = getHighestSafeHeart(hand, playedCards, leadPlayer);
				//System.out.println("HEART MODULE GETSAFESTHEART " + returnCard);
				if(returnCard == null){
					returnCard = findLowestHeart(hand);
					//System.out.println("HEART MODULE FINDLOWESTHEART " + returnCard);
				}
			}
		}		
		return returnCard;
	}

	public Card playHighestHeartIfLost(List<Card> hand, Card[] playedCards, MaxFourInt leadPlayer){
		Card returnCard = null;
		int highestValue = 0;
		int numOfPlayersPlayed = 0;
	
		for(int i=0; i<playedCards.length; i++){
			if(playedCards[i] != null){
				numOfPlayersPlayed++;
			}
		}	
		
		if(numOfPlayersPlayed == 3){
			for (int i = 0; i < hand.size(); i++) {
				Card card = hand.get(i);
				if (card.getSuit() == Card.HEARTS && card != Card.KING_HEARTS && card != Card.ACE_HEARTS && card != Card.QUEEN_HEARTS) {
					int value = card.getValue();
					if (value > highestValue) {
						highestValue = value;
						returnCard = card;
					}
				}
			}
		}
		
		return returnCard;
	}
	
	public Card getHighestSafeHeart(List<Card> hand, Card[] playedCards, MaxFourInt leadPlayer){
		Card returnCard = null;
		char suit =  Card.HEARTS;
		
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
	
	public Card findHighestHeart(List<Card> hand){
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
	
	public Card findLowestHeart(List<Card> hand){
		int lowestHeartValue = 15;
		Card lowestHeart = null;
		
		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit() == Card.HEARTS){
				int value = hand.get(i).getValue();
				if(value < lowestHeartValue){
					lowestHeartValue = value;
					lowestHeart = card;
				}
			}
		}
		return lowestHeart;
	}
}
