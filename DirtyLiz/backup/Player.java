import java.util.*;

public class Player {

	private List<Card> hand;
	private int score;
	private int scoreForCurrentHand;
	private int wins;
	private int losses;
	private ArtificialIntelligence ai;
	private int noOfShootTheMoons;
	
	public Player(ArtificialIntelligence ai){
		this.ai = ai; 
		ai.connectPlayer(this);
		noOfShootTheMoons = 0;
	}
	
	public void giveCards(List<Card> hand){
		this.hand = hand;
	}
	
	public void addToScore(int score){
		this.scoreForCurrentHand += score;
	}
	
	public void shootMoon(boolean success) {
		if(success) {
			scoreForCurrentHand = 0;
			noOfShootTheMoons++;
		} else {
			scoreForCurrentHand = 36;
		}
	}
	
	public void resetScore(){
		score = 0;
	}
	
	public void endHand() {
		score += scoreForCurrentHand;
		scoreForCurrentHand = 0;
	}
	
	public int getScoreForCurrentHand() {
		return scoreForCurrentHand;
	}
	
	public Card promptMove(Card[] playedCards, Player[] players){
		Card toBePlayed = ai.getMove(playedCards, hand, players); 
		hand.remove(toBePlayed);
		
		return toBePlayed; 
	}
	
	public boolean shouldPlay() {
		return !hand.isEmpty();
	}
	
	public int getScore(){
		return score;
	}
	
	public ArtificialIntelligence getAI(){
		return ai;
	}
	
	public void lose() {
		losses ++;
	}
	
	public void win() {
		wins++;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public int getShootTheMoon() {
		return noOfShootTheMoons;
	}
}
