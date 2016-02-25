package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class SmartAI1 extends SmartArtificialIntelligence {
	
	private Player connectPlayer;
	private ShootingMoonModule shootingMoon;
	private PlayRecklesslyModule reckless;
	private PlaySuperSafeModule safe;

	
	public SmartAI1(CardTracker cardTracker) {
		super(cardTracker);
		reckless = new PlayRecklesslyModule();
		safe = new PlaySuperSafeModule();
		shootingMoon = new ShootingMoonModule();
		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer) {
		
		if(shootingMoon.shootingTheMoon(hand, players, connectPlayer)){
			return reckless.getMove(playedCards, hand,leadPlayer);
		}
		return safe.getMove(playedCards, hand, leadPlayer);
	}

	public boolean isSmart() {
		return true;
	}
	
	public String toString(){
		String ai = "Smart AI 1";
		return ai;	
	}

	public void connectPlayer(Player player) {
		connectPlayer = player;
	}
	
	public Player getPlayer(){
		return connectPlayer;
	}

}
