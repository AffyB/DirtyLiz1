import java.util.ArrayList;
import java.util.List;

public class LostAllHopeModule {
public Card getMove(Card[] playedCards, List<Card> hand){
		
		Card cardToPlay = null;
		if(playedCards[0] == null){
			cardToPlay = playRandomCard(hand);
			return cardToPlay;
			
		}
		
		char suitPlayed = playedCards[0].getSuit();
		
		cardToPlay = checkForSameSuit(suitPlayed, hand);
		if(cardToPlay != null){
			return cardToPlay;
		}
		
		cardToPlay = checkForHeart(hand);
		if(cardToPlay != null){
			return cardToPlay;
		}
		
		return cardToPlay = playRandomCard(hand);
	
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
	
	public Card checkForHeart(List<Card> hand){
		ArrayList<Card> heartHand = new ArrayList<Card>(); 
		Card heartToBePlayed = null;
		char heart = 'H'; 

		for(int i=0; i<hand.size(); i++){
			Card card = hand.get(i);
			if(card.getSuit() == heart){
				heartHand.add(card);
				}
			}
		
		int length = heartHand.size();
		double randomCard = Math.floor(Math.random() * length);
		if(!heartHand.isEmpty()) {
			heartToBePlayed = heartHand.get((int) randomCard);
		}
		
		return heartToBePlayed;
	}
	
	public Card playRandomCard(List<Card> hand){
		Card cardToBePlayed = null;
		double randomCard = Math.floor(Math.random() * hand.size());
		cardToBePlayed = hand.get((int) randomCard);

		return cardToBePlayed; 
	}
}
