package ai;

import java.util.List;
import commmon.Card;
import commmon.MaxFourInt;

public class QueenOfSpadesModule implements Module {

	private CardTracker tracker;
	
	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}
	
	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;

		if(playedCards[leadPlayer.getValue()] == null) {
			if(hand.contains(Card.QUEEN_SPADES)){
				returnCard = getCardThatCanBeVoided(hand);
				System.out.println("LEAD CARD RETURNED BY QUEEN MODULE HAVE QUEEN AND RETURN VOIDED CARD " + returnCard);
				//return returnCard;
			}
		}
		
		if(playedCards[leadPlayer.getValue()] == null) {
			if(returnCard==null){
				if(hand.contains(Card.QUEEN_SPADES)){
					returnCard = tracker.getNonLosableCard(hand);
					System.out.println("LEAD CARD RETURNED BY QUEEN MODULE HAVE QUEEN AND RETURN NONELOSABLECARD " + returnCard);
					//return returnCard;
				}
			}
		}
		
		if(playedCards[leadPlayer.getValue()] == null) {
			if(returnCard==null){
				if(hand.contains(Card.QUEEN_SPADES)){
					returnCard = getLowestCard(hand);
					System.out.println("LEAD CARD RETURNED BY QUEEN MODULE HVE QUEEN AND RETURN LOWEST CARD " + returnCard);
					//return returnCard;
				}
			}
		}

		if(playedCards[leadPlayer.getValue()] != null){
			if (playedCards[leadPlayer.getValue()].getSuit() == Card.SPADES){
				if (hand.contains(Card.QUEEN_SPADES)) {
					returnCard = getHighestSpade(hand);
				}
			}
		}
		
		return returnCard;
	}
	
	
	public Card checkForQueenAndReturn(List<Card> hand){
		if (hand.contains(Card.QUEEN_SPADES)) {
			return Card.QUEEN_SPADES;
		}
		return null;	
	}
	
	public Card getHighestSpade(List<Card> hand){
		Card returningCard = null;
		int highestValue = 0;
		Card tempCard = null;
		int tempValue = 0;
		 
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) != Card.QUEEN_SPADES && hand.get(i).getSuit() == Card.SPADES){
				tempCard = hand.get(i);
				tempValue = tempCard.getValue();
				if(tempValue > highestValue){
					highestValue = tempValue;
					returningCard = tempCard;
				}
			}
		}
		
		if(returningCard == null){
			returningCard = checkForQueenAndReturn(hand);
		}
		return returningCard;
	}
	
//	public Card getNonHeartOrSpadeLeadCard(List<Card>hand){
//		Card returnCard = null;
//		
//		for(int i=0; i<hand.size(); i++){
//			if(hand.get(i).getSuit() != Card.SPADES){
//				if(hand.get(i).getValue() <5){
//					returnCard = hand.get(i);
//				}
//			}
//		}
//		
//		return returnCard;
//	}
	
	public boolean isThereEnoughCoverForQueen(List<Card> hand){
		int numOfSpadeCards = tracker.numberOfTimesSpadesIsLead();
		
		for(int i=0; i<hand.size(); i++){
			char suitPlayed = hand.get(i).getSuit();
			if(suitPlayed == Card.SPADES){
				numOfSpadeCards++;	
			}
		}
		return numOfSpadeCards >= 3;
	}
	
	private Card getCardThatCanBeVoided(List<Card> hand){
		Card returnCard = null;
		int numOfClubs = 0;
		int numOfDiamonds = 0;
		int numOfHearts = 0;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getSuit() == Card.CLUBS){
				numOfClubs++;
			}else if(hand.get(i).getSuit() == Card.DIAMONDS){
				numOfDiamonds++;
			}else if(hand.get(i).getSuit() == Card.HEARTS){
				numOfHearts++;
			}
		}
		
		if(numOfClubs==1){
			for(int i=0; i<hand.size(); i++){
				if(hand.get(i).getSuit() == Card.CLUBS){
					returnCard = hand.get(i);
				}
			}
		}else if(numOfDiamonds==1){
			for(int i=0; i<hand.size(); i++){
				if(hand.get(i).getSuit() == Card.DIAMONDS){
					returnCard = hand.get(i);
				}
			}
		}else if(numOfHearts==1){
			for(int i=0; i<hand.size(); i++){
				if(hand.get(i).getSuit() == Card.HEARTS){
					returnCard = hand.get(i);
				}
			}
		}
		return returnCard;
	}
	
	private Card getLowestCard(List<Card> hand){
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

}
