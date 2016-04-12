package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.ArtificialIntelligence;
import ai.PlayHighAI;
import ai.PlayLowAI;
import ai.PlayRandomAI;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class AITest {
	
	private ArtificialIntelligence ai;
	ArrayList<Card> cardsPlayedTracker;
	private List<Card> hand;
	private Card move;
	MaxFourInt currentPlayer;
	Player[] players;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		currentPlayer = new MaxFourInt(0);
		players = new Player[3];
	}

	@Test
	public void testHighAIPlaysHighestHeart() {
		// test to return the highest Hearts available when not able to follow
		// Suit
		ai = new PlayHighAI();

		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.FOUR_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.TWO_HEARTS);

		Card[] playedCards = { Card.ACE_SPADES, Card.EIGHT_SPADES };
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);

		move = ai.getMove(playedCards, hand, players, currentPlayer);

		assertEquals("Expected: " + Card.FIVE_HEARTS.toString() + " Actual: " + move.toString(), Card.FIVE_HEARTS, move);

	}
	
	@Test
	public void testHighAIFollowSuit() { 
		// test to return highest card of the same suit
		ai = new PlayHighAI();
		
		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.KING_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS, Card.EIGHT_CLUBS};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.ACE_CLUBS.toString() + "Actual: " + move.toString(), Card.ACE_CLUBS, move);
	
	}
	
	@Test
	public void testHighAIPlayNextHighestCardNotFollowingSuit(){
	//play next highest suit if unable to follow Suit
		ai = new PlayHighAI();
		
		hand.add(Card.SEVEN_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.NINE_DIAMONDS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		
		Card[] playedCards = {Card.ACE_SPADES, Card.FIVE_SPADES};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.NINE_DIAMONDS.toString() + "Actual: " + move.toString(), Card.NINE_DIAMONDS, move);
		
		}
	
	@Test
	public void testLowAIFollowSuit(){
	// test to return lowest card of the same suit
		ai = new PlayLowAI();
		
		hand.add(Card.SEVEN_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.NINE_DIAMONDS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		
		Card[] playedCards = {Card.TEN_DIAMONDS};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.TWO_DIAMONDS.toString() + "Actual: " + move.toString(), Card.TWO_DIAMONDS, move);
		
	}
	
	@Test
	public void testLowAIPlayLowestHeart(){
	// test to return lowest heart if unable to follow suit and hearts available
		ai = new PlayLowAI();
		
		hand.add(Card.ACE_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.FOUR_HEARTS);
		hand.add(Card.TWO_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.TWO_HEARTS.toString() + "Actual: " + move.toString(), Card.TWO_HEARTS, move);
		
	}
	
	@Test
	public void testLowAIPlayNextLowestCardNotFollowingSuit(){
	// test to return next lowest card if unable to follow suit or play heart
		ai = new PlayLowAI();
		
		hand.add(Card.ACE_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		hand.add(Card.FIVE_CLUBS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.FOUR_CLUBS);
		hand.add(Card.THREE_CLUBS);
		
		Card[] playedCards = {Card.SEVEN_SPADES};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.TWO_DIAMONDS.toString() + "Actual: " + move.toString(), Card.TWO_DIAMONDS, move);
		
	}
	
	@Test
	public void testRandomAIFollowSuit(){
	// test to return random card following suit
		ai = new PlayRandomAI();
		
		hand.add(Card.ACE_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		hand.add(Card.FIVE_CLUBS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.FOUR_CLUBS);
		hand.add(Card.THREE_CLUBS);
		
		Card[] playedCards = {Card.SEVEN_DIAMONDS};
		cardsPlayedTracker.add(Card.EIGHT_DIAMONDS);
		cardsPlayedTracker.add(Card.ACE_SPADES);
		cardsPlayedTracker.add(Card.KING_SPADES);
		
		move = ai.getMove(playedCards, hand, players, currentPlayer);
		assertEquals("Expected: " + Card.DIAMONDS + "Actual: " + move.toString(), Card.DIAMONDS, move.getSuit());
		
	}
	

}
