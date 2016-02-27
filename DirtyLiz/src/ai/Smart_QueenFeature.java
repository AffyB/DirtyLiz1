package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_QueenFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModule safe;
	private PlayRecklesslyModule reckless;
	private QueenOfSpadesModule queenModule;
	
	public Smart_QueenFeature(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule();
		queenModule = new QueenOfSpadesModule();
		reckless = new PlayRecklesslyModule();
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer) {
		Card returnCard = null;
		
		returnCard = queenModule.getMove(playedCards, hand, leadPlayer);
		
		if(returnCard==null){
			returnCard = safe.getMove(playedCards, hand, leadPlayer);
		}
		
		return returnCard;
	}
	
	public String toString(){
		String ai = "Smart_QueenFeatureAI";
		return ai;	
	}
	
	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;
	}

}
