package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class PlayHighAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private PlayHighModule reckless;

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		reckless = new PlayHighModule();
		return reckless.getMove(playedCards, hand, leadPlayer);
	}
	
	public String toString(){
		String ai = "HighAI";
		return ai;	
	}

	public boolean isSmart() {
		return false;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}

