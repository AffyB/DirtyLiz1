package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import ai.PlayRandomAI;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class testPlayer {

	//private artificialIntelligence ai; 
	private List<Card> hand;
	//private Card move;
	private Player player;
	ArrayList<Card> cardsPlayedTracker;
	MaxFourInt leadPlayer;
	Player[] players;
	
	@Before
	public void setUp() {
		player = new Player(new PlayRandomAI());
		hand = new ArrayList<Card>();
		cardsPlayedTracker = new ArrayList<Card>();
		leadPlayer = new MaxFourInt(0);
		players = new Player[3];
	}

	
	@Test
	public void testAddToScore() {
		
		player.addToScore(10);
		player.addToScore(20);
		assertEquals("Expected: " + 30 + " Actual: " + player.getScoreForCurrentHand(), 30, player.getScoreForCurrentHand());
		
	}
	
	@Test
	public void testEndHand() {
		player.addToScore(10);
		player.addToScore(20);
		assertEquals("Expected: " + 0 + " Actual: " + player.getScore(), 0, player.getScore());
		player.endHand();
		assertEquals("Expected: " + 30 + " Actual: " + player.getScore(), 30, player.getScore());
	}
	
	@Test
	public void testResetScore(){
		
		player.addToScore(30);
		player.resetScore();
		assertEquals("Expected: " + 0 + " Actual: " + player.getScore(), 0, player.getScore());
		
	}
	
	@Test
	public void testHandRemoveCard(){
		
		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.FOUR_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.TWO_DIAMONDS);
		
		Card[] playedCards = {Card.TEN_DIAMONDS};
		player.giveCards(hand);
		player.promptMove(playedCards, players, leadPlayer);
		assertFalse(hand.contains(Card.TWO_DIAMONDS));
	}
}
