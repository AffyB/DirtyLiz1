package ai;

import java.util.List;
import commmon.Card;
import commmon.MaxFourInt;

public class QueenOfSpadesModule {
	
	private boolean isQueenPresent;
	private boolean isAcePresent;
	private boolean isKingPresent;
	private boolean playHigh;
	private boolean playLow;
	
	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;
		checkForQueen(hand);
		checkForAceOfSpades(hand);
		checkForKingOfSpades(hand);
		
		
		
		if (playedCards[leadPlayer.getValue()].getSuit() == Card.SPADES){
			if (isQueenPresent) {
				if (isAcePresent) {
					int pos = whereIsAce(hand);
					returnCard = hand.get(pos);
				} else if (isKingPresent) {
					int poss = whereIsKing(hand);
					returnCard = hand.get(poss);
				}
			}else{
				playHigh = true;
			}
		}else{
			playLow = true;
		}
		
		return returnCard;
	}
	
	
	public boolean checkForQueen(List<Card> hand){
		isQueenPresent = false;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.QUEEN_SPADES){
				isQueenPresent = true;
			}
		}
		return isQueenPresent;
	}
	
	public int whereIsQueen(List<Card> hand){
		int queenPosition = 0;
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.QUEEN_SPADES){
				queenPosition = i;
			}
		}
		return queenPosition;	
	}
	
	public boolean checkForAceOfSpades(List<Card> hand){
		isAcePresent = false;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.ACE_SPADES){
				isAcePresent = true;
			}
		}
		return isAcePresent;
	}
	
	public int whereIsAce(List<Card> hand){
		int acePosition = 0;
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.ACE_SPADES){
				acePosition = i;
			}
		}
		return acePosition;	
	}
	
	public boolean checkForKingOfSpades(List<Card> hand){
		isKingPresent = false;
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.KING_SPADES){
				isKingPresent = true;
			}
		}
		return isKingPresent;
	}
	
	public int whereIsKing(List<Card> hand){
		int kingPosition = 0;
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i) == Card.KING_SPADES){
				kingPosition = i;
			}
		}
		return kingPosition;	
	}
	
	public boolean playHigh(){
		return playHigh;
	}
	
	public boolean playLow(){
		return playLow;
	}
}
