import java.util.List;

public class SmartAI1 extends SmartArtificialIntelligence {
	
	private Player connectPlayer;
	private ShootingMoonModule shootingMoon;
	private PlayRecklesslyModule reckless;
	private PlaySafeModule safe;

	
	public SmartAI1(CardTracker cardTracker) {
		super(cardTracker);
		reckless = new PlayRecklesslyModule();
		safe = new PlaySafeModule();
		shootingMoon = new ShootingMoonModule();
		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players) {
		
		if(shootingMoon.shootingTheMoon(hand, players, connectPlayer)){
			return reckless.getMove(playedCards, hand);
		}
		return safe.getMove(playedCards, hand);
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
