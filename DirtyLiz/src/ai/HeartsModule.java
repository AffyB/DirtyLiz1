package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class HeartsModule implements Module{
	
	private CardTracker tracker;
	
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
			returnCard = ifHeartsLead(hand, playedCards, leadPlayer);
			if(returnCard == null){
				returnCard = findLowestHeart(hand);
			}
		}
		
		return returnCard;
	}
	
	public Card ifHeartsLead(List<Card> hand, Card[] playedCards, MaxFourInt leadPlayer){
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
	
	public boolean enoughHeartsCover(List<Card> hand){
		boolean enoughHeartsCover = false;
		int numOfLowHearts = 0;
		int numOfMidHearts = 0;
		int numOfDangerHearts = 0;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getValue() <= 5){
				numOfLowHearts++;
			}
			else if(hand.get(i).getValue() > 5 && hand.get(i).getValue() <= 10){
				numOfMidHearts++;
			}
			else if(hand.get(i).getValue() >= 11){
				numOfDangerHearts++;
			}
		}
		
		// no danger hearts then dont need cover 
		if(numOfDangerHearts == 0){
			return true;
		}

		if(tracker.numberOfTimesHeartsIsLead() == 0 ){
			if(numOfLowHearts >= 2 || numOfLowHearts >= 1 && numOfMidHearts >=1){
				enoughHeartsCover = true;
			}
		} else if(tracker.numberOfTimesHeartsIsLead() == 1 ){
			if(numOfLowHearts >= 1 || numOfMidHearts >=1){
				enoughHeartsCover = true;
			}
		} else {
			if(findBestHeartInHand(hand) == null){
				enoughHeartsCover = true;
			}else if(tracker.isTheBestHeartInHandBeatable(findBestHeartInHand(hand), hand)){
				enoughHeartsCover = true;
			}
		}
		return enoughHeartsCover;
	}

	private Card findBestHeartInHand(List<Card> hand){
		Card bestHeartInHand = null;
		int points = 6;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getSuit() == Card.HEARTS && hand.get(i).getPoints() < points){
				points = hand.get(i).getPoints();
				bestHeartInHand = hand.get(i);
			}
		}
		return bestHeartInHand;
	}
}
