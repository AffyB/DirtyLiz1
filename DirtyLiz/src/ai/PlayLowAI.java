package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class PlayLowAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private PlaySuperSafeModule safe;
	
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		safe = new PlaySuperSafeModule();
		return safe.getMove(playedCards, hand, leadPlayer);	
	}
	
	public String toString(){
		String ai = "LowAI";
		return ai;	
	}

	public boolean isSmart() {
		return false;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}
