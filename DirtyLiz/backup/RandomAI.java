import java.util.List;

public class RandomAI implements ArtificialIntelligence {
	
	Player connectedPlayer; 
	private LostAllHopeModule lost;

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players){
		lost = new LostAllHopeModule();
		return lost.getMove(playedCards, hand);
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

