package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_ShootTheMoonOnLow extends SmartArtificialIntelligence{

	/** Shoot the Moon module added to Low AI **/

	Player connectedPlayer; 
	private PlaySuperSafeModule safe;
	private ShootingMoonModule shootTheMoon;
	
	public Smart_ShootTheMoonOnLow(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySuperSafeModule();
		shootTheMoon = new ShootingMoonModule();
	}
	
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer){
		Card returnCard = null;
		
		returnCard = shootTheMoon.getMove(playedCards, hand, leadPlayer, players, currentPlayer);
		
		if(returnCard == null) {
			returnCard = safe.getMove(playedCards, hand, leadPlayer);	
		}
		
		return returnCard;
	}
	
	public String toString(){
		String ai = "Smart_ShootOnLowAI";
		return ai;	
	}

	public boolean isSmart() {
		return true;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}
