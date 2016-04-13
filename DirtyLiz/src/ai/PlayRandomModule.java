package ai;
import java.util.ArrayList;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class PlayRandomModule {
public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer){
		
		Card cardToPlay = null;
		if(playedCards[leadPlayer.getValue()] == null){
			cardToPlay = playRandomCard(hand);
			return cardToPlay;
		}
		
		char suitPlayed = playedCards[leadPlayer.getValue()].getSuit();
		
		cardToPlay = checkForSameSuit(suitPlayed, hand);
		if(cardToPlay != null){
			return cardToPlay;
		}
		
		return playRandomCard(hand);
	
	}
	
	public Card checkForSameSuit(char suit, List<Card> hand){
		ArrayList<Card> sameSuitHand = new ArrayList<Card>(); 
		Card cardToBePlayed = null;

		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit()==(suit)){
				sameSuitHand.add(card);
			}
		}
		
		int length = sameSuitHand.size();
		double randomCard = Math.floor(Math.random() * length);
		if(!sameSuitHand.isEmpty()) {
			cardToBePlayed = sameSuitHand.get((int) randomCard);
		}
		
		return cardToBePlayed;
		
	}
	
	public Card playRandomCard(List<Card> hand){
		Card cardToBePlayed = null;
		double randomCard = Math.floor(Math.random() * hand.size());
		cardToBePlayed = hand.get((int) randomCard);

		return cardToBePlayed; 
	}
}
