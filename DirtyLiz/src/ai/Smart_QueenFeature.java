package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_QueenFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModule safe;
	private PlayRecklesslyModule reckless;
	private QueenOfSpadesModule queenModule;
	
	public Smart_QueenFeature(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule();
		queenModule = new QueenOfSpadesModule();
		reckless = new PlayRecklesslyModule();
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt currentPlayer) {
		Card returnCard = null;
		
		returnCard = queenModule.getMove(playedCards, hand, currentPlayer);
		if(returnCard==null){
			if(queenModule.playLow()){
				returnCard = safe.getMove(playedCards, hand, currentPlayer);
			}
			else if(queenModule.playHigh()){
				returnCard = reckless.getMove(playedCards, hand, currentPlayer);
			}
		}
		return returnCard;
	}

	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;
	}

}
