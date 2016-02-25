package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public interface ArtificialIntelligence {
	
	Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt currentPlayer);
	String toString();
	boolean isSmart();
	void connectPlayer(Player player); 
}