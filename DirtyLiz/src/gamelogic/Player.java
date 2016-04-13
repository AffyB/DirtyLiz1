package gamelogic;
import java.util.*;

import ai.ArtificialIntelligence;
import commmon.Card;
import commmon.MaxFourInt;

public class Player {

	private List<Card> hand;
	private int score;
	private int scoreForCurrentHand;
	private int wins;
	private int losses;
	private ArtificialIntelligence ai;
	private int noOfShootTheMoons;
	
	//sets player to the AI and vice versa
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
	
	//notes if a score is 36 (shot the moon) and awards penalty points to everyone else
	//takes note of the number of times player shoots the moon
	public void shootMoon(boolean success) {
		if(success) {
			scoreForCurrentHand = 0;
			noOfShootTheMoons++;
		} else {
			scoreForCurrentHand = 36;
		}
	}
	
	//reset running score
	public void resetScore(){
		score = 0;
	}
	
	//adds score for round played to running score and resets score for current hand
	public void endHand() {
		score += scoreForCurrentHand;
		scoreForCurrentHand = 0;
	}
	
	public int getScoreForCurrentHand() {
		return scoreForCurrentHand;
	}
	
	//calls for a card to be played by the AI and removes said card from the player's hand
	public Card promptMove(Card[] playedCards, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer){
		//System.out.println("HAAAAND BEFRE " + hand);
		Card toBePlayed = ai.getMove(playedCards, hand, players, leadPlayer, currentPlayer); 
		//System.out.println("Card Played: " + toBePlayed);
		hand.remove(toBePlayed);
		//System.out.println("HAAANNND AFTERRRR " + hand);
		return toBePlayed; 
	}
	
	//returns false if hand is empty
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
	
	public int getHandSize() {
		return hand.size();
	}
	
}
