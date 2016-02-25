import java.util.List;

public interface ArtificialIntelligence {
	
	Card getMove(Card[] playedCards, List<Card> hand, Player[] players);
	String toString();
	boolean isSmart();
	void connectPlayer(Player player); 
}
