package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class PlaySuperSafeModule {
	
	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer){
		
		Card cardToPlay = null;
		if(playedCards[leadPlayer.getValue()] == null){
			cardToPlay = playLowestCard(hand);
			return cardToPlay;
		}
		
		char suitPlayed = playedCards[leadPlayer.getValue()].getSuit();
		
		cardToPlay = checkForSameSuit(suitPlayed, hand);
		if(cardToPlay != null){
			return cardToPlay;
		}
		
		if(hand.contains(Card.QUEEN_SPADES)){
			return Card.QUEEN_SPADES;
		}
		
		cardToPlay = checkForHeart(hand);
		if(cardToPlay != null){
			return cardToPlay;
		}
		
		return cardToPlay = playLowestCard(hand);
	
	}
	
	public Card checkForSameSuit(char suit, List<Card> hand){
		int lowestValue = 15;
		Card lowestValueCard = null;

		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit()==(suit)){
				int value = card.getValue();
				if(value<lowestValue){
					lowestValue = value;
					lowestValueCard = card;
				}
			}
		}
		return lowestValueCard;
	}
	
	public Card checkForHeart(List<Card> hand){
		Card findHeart = null;
		int lowestValue = 15;
		
		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit() == Card.HEARTS){
				int value = card.getValue();
				if(value<lowestValue){
					lowestValue = value;
					findHeart = card;
				}
			}
		}
		return findHeart;
	}
	
	public Card playLowestCard(List<Card> hand){
		int lowestValue = 15;
		Card lowestValueCard = null;
		
		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			int value = card.getValue();
			if(value<lowestValue){
				lowestValue = value;
				lowestValueCard = card;
			}
		}
	return lowestValueCard; 
	}

}
