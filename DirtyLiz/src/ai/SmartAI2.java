package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class SmartAI2 extends SmartArtificialIntelligence {

	private Player connectPlayer;
	private StopShootingTheMoonModule stopShootingMoon;
	private ShootingMoonModule shootingMoon;
	private PlayRecklesslyModule reckless;
	private PlaySafeModule safe;

	public SmartAI2(CardTracker cardTracker) {
		super(cardTracker);
		reckless = new PlayRecklesslyModule();
		safe = new PlaySafeModule();
		stopShootingMoon = new StopShootingTheMoonModule();
		shootingMoon = new ShootingMoonModule();

	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer) {

		if (stopShootingMoon.stopShootingTheMoon(hand, players, connectPlayer)
				|| shootingMoon.shootingTheMoon(hand, players, connectPlayer)) {
			return reckless.getMove(playedCards, hand, leadPlayer);
		} else {
			return safe.getMove(playedCards, hand, leadPlayer);
		}
	}

	public boolean isSmart() {
		return true;
	}

	public String toString() {
		String ai = "Smart AI 2";
		return ai;
	}

	public void connectPlayer(Player player) {
		connectPlayer = player;
	}

	public Player getPlayer() {
		return connectPlayer;
	}

}
