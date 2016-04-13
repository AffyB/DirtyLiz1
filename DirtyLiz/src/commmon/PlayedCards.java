package commmon;

import java.util.HashMap;

public class PlayedCards {
	
	private HashMap<MaxFourInt,Card> playedCards;


	//create hashmap to track the cards played
	public PlayedCards() {
		playedCards = new HashMap<>();
	}
	
	//add player and card played by the player to the hashmap
	public void playCard(MaxFourInt player, Card card) {
		playedCards.put(player, card);
	}
}
