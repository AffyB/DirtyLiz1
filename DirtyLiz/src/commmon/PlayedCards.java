package commmon;

import java.util.HashMap;

public class PlayedCards {
	
	private HashMap<MaxFourInt,Card> playedCards;


	public PlayedCards() {
		playedCards = new HashMap<>();
	}
	
	public void playCard(MaxFourInt player, Card card) {
		playedCards.put(player, card);
	}
}
