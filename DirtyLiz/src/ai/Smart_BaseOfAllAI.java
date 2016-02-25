package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_BaseOfAllAI extends SmartArtificialIntelligence{

	Player connectedPlayer; 
	private PlaySafeModule safe;
	
	public Smart_BaseOfAllAI(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule();
	}
	
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		return safe.getMove(playedCards, hand, leadPlayer);	
	}
	
	public String toString(){
		String ai = "Smart_BaseAI";
		return ai;	
	}

	public boolean isSmart() {
		return true;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}
