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
		int highestHeartValue = 0;
		Card highestHeart = null;
		
		for(int i=0; i<hand.size(); i++){
			Card temp = hand.get(i);
			int tempVal = temp.getValue();
			if(tempVal > highestHeartValue){
				highestHeart = temp;
				highestHeartValue = tempVal;
			}
		}
		
		return highestHeart;
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
		int numeOfMidHearts = 0;
		int numOfHighHearts = 0;
		int numOfDangerHearts = 0;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getValue() <= 5){
				numOfLowHearts++;
			}
			else if(hand.get(i).getValue() > 5 && hand.get(i).getValue() <= 8){
				numeOfMidHearts++;
			}
			else if(hand.get(i).getValue() > 8 && hand.get(i).getValue() <= 11){
				numOfHighHearts++;
			}
			else if(hand.get(i).getValue() > 11){
				numOfDangerHearts++;
			}
		}
		
		if(tracker.numberOfTimesHeartsIsLead() <=1 ){
			if(numOfLowHearts >= 2 || numeOfMidHearts >= 1){
				enoughHeartsCover = true;
			}
		}
			
		if(tracker.numberOfTimesHeartsIsLead() >=2 ){
			if(numOfLowHearts >= 1 || numeOfMidHearts >= 1){
				enoughHeartsCover = true;
			}
		}
		
		return enoughHeartsCover;
	}

}
