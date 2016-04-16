package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_SpadeFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModule2 safe;
	private SpadesModule queenModule;
	
	public Smart_SpadeFeature(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule2();
		queenModule = new SpadesModule();
	}

	//Calls spade module and play safe module 
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer) {
		Card returnCard = null;
		queenModule.addTracker(cardTracker);
		
		returnCard = queenModule.getMove(playedCards, hand, leadPlayer);
		
		if(returnCard==null){
			returnCard = safe.getMove(playedCards, hand, leadPlayer);
		}
		//System.out.println("FROM SPADE MODULE " + returnCard);
		return returnCard;
	}
	
	public String toString(){
		String ai = "Smart_SpadeFeatureAI";
		return ai;	
	}
	
	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;
	}

}
