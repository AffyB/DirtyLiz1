import java.util.List;

public class PlayLowAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private PlaySafeModule safe;
	
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players){
		safe = new PlaySafeModule();
		return safe.getMove(playedCards, hand);	
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
