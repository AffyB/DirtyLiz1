package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_ShootTheMoonFeature extends SmartArtificialIntelligence{

	Player connectedPlayer; 
	private PlaySafeModule safe;
	private ShootingMoonModule shootTheMoon;
	private PlayRecklesslyModule reckless;
	
	public Smart_ShootTheMoonFeature(CardTracker cardTracker) {
		super(cardTracker);
		safe = new PlaySafeModule();
		shootTheMoon = new ShootingMoonModule();
		reckless = new PlayRecklesslyModule();
	}
	
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		if (shootTheMoon.shootingTheMoon(hand, players, leadPlayer)) {
			return reckless.getMove(playedCards, hand, leadPlayer);
		} else {
			return safe.getMove(playedCards, hand, leadPlayer);
		}
	}
	
	public String toString(){
		String ai = "Smart_BaseAI+ShootingMoon";
		return ai;	
	}

	public boolean isSmart() {
		return true;
	}
	
	public void connectPlayer(Player player){
		connectedPlayer = player;
	}
}
