package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class RandomAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private LostAllHopeModule lost;

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		lost = new LostAllHopeModule();
		return lost.getMove(playedCards, hand, leadPlayer);
	}
	
	public String toString(){
		String ai = "RandomAI";
		return ai;	
	}

	public boolean isSmart() {
		return false;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}

