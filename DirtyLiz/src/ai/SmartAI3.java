package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class SmartAI3 extends SmartArtificialIntelligence {

	private Player connectPlayer;
	private PlayRecklesslyModule reckless;
	private PlaySuperSafeModule safe;
	
	public SmartAI3(CardTracker cardTracker) {
		super(cardTracker);
		reckless = new PlayRecklesslyModule();
		safe = new PlaySuperSafeModule();
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt currentPlayer) {
		//int handSize = connectPlayer.getHandSize();
		char suit = playedCards[currentPlayer.getValue()].getSuit(); 
		int suitLeadNumber = 0;
		if(suit == Card.CLUBS){
			suitLeadNumber = cardTracker.numberOfTimesClubsIsLead();
		}
		else if(suit == Card.DIAMONDS){
			suitLeadNumber = cardTracker.numberOfTimesDiamondsIsLead();
		}
		else if(suit == Card.HEARTS){
			suitLeadNumber = cardTracker.numberOfTimesHeartsIsLead();
		}
		else if(suit == Card.SPADES){
			suitLeadNumber = cardTracker.numberOfTimesSpadesIsLead();
		}
		
		if(suitLeadNumber < 2){
			return reckless.getMove(playedCards, hand, currentPlayer);
		}
		return safe.getMove(playedCards, hand, currentPlayer);
	}

	public boolean isSmart() {
		return true;
	}
	
	public String toString(){
		String ai = "Smart AI 3";
		return ai;	
	}

	public void connectPlayer(Player player) {
		connectPlayer = player;
	}
	
	public Player getPlayer(){
		return connectPlayer;
	}
	
	

}
