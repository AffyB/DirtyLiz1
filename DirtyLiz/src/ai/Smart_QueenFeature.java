package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_QueenFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModule safe;
	private SpadesModule queenModule;
	
	public Smart_QueenFeature(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule();
		queenModule = new SpadesModule();
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer) {
		Card returnCard = null;
		queenModule.addTracker(cardTracker);
		
		returnCard = queenModule.getMove(playedCards, hand, leadPlayer);
		
		if(returnCard==null){
			returnCard = safe.getMove(playedCards, hand, leadPlayer);
		}
		//System.out.println("FROM QUEEN MODULE " + returnCard);
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
