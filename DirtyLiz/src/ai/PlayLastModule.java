package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class PlayLastModule implements Module {
	//when your the last player, and there are no points - play the highest card you have 
	
	private CardTracker tracker;
	
	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}
	
	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;
		int numOfPlayersPlayed = 0;
		int points = 0;
	
		for(int i=0; i<playedCards.length; i++){
			if(playedCards[i] != null){
				points = points + playedCards[i].getPoints();
				numOfPlayersPlayed++;
			}
		}
		
		//TODO Test points values
		if(numOfPlayersPlayed == 3 && points == 0){
			returnCard = getHighestCardThatFollowsSuit(hand, playedCards[leadPlayer.getValue()].getSuit());
		}
		
		if(returnCard == null) {
			getHighClubOrDiamond(hand, playedCards[leadPlayer.getValue()].getSuit());
		}
		return returnCard;
	}
	
	private Card getHighestCardThatFollowsSuit(List<Card> hand, char suit){
		Card returnCard = null;
		int highestValue = 0;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getSuit() == suit){
				Card tempCard = hand.get(i);
				int tempValue = tempCard.getValue();
				if(tempCard != Card.QUEEN_SPADES){
					if(tempValue > highestValue){
						highestValue = tempValue;
						returnCard = tempCard;
					}
				}
			}
		}
		return returnCard;
	}
	
	private Card getHighClubOrDiamond(List<Card> hand, char suit) {
		if(suit == Card.SPADES || suit == Card.HEARTS) {
			return null;
		}
		int howManyTimesSuitHasBeenLead = tracker.numberOfTimesSuitIsLead(suit);

		Card highestCard = null;
		int highestValueSoFar = 0;
		Card currentCard = null;
		for (int i = 0; i < hand.size(); i++) {
			currentCard = hand.get(i);
			if (currentCard.getSuit() == suit && currentCard.getValue() > highestValueSoFar) {
				highestValueSoFar = currentCard.getValue();
				highestCard = currentCard;
			}
		}
		
		if(howManyTimesSuitHasBeenLead < 2 && hand.size() > 5) {
			return highestCard;
		}
		
		return null;
	}
	
}
