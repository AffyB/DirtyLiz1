import java.util.List;

public class PlayHighAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private PlayRecklesslyModule reckless;

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players){
		reckless = new PlayRecklesslyModule();
		return reckless.getMove(playedCards, hand);
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

