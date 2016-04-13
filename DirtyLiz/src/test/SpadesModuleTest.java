package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.SpadesModule;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class SpadesModuleTest {
	
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	private SpadesModule queen;
	Player[] players;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		queen = new SpadesModule();
		cardsPlayed = new Card[3];
		players = new Player[3];
		suit = Card.SPADES;
		leadPlayer = new MaxFourInt(0);
	}
	
	@Test
	public void testGetMove() {
		hand.add(Card.QUEEN_SPADES);
		hand.add(Card.TEN_DIAMONDS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.TEN_SPADES);
		
		cardsPlayed[0] = Card.FIVE_SPADES;
		cardsPlayed[1] = Card.SEVEN_SPADES;
	
		assertEquals(Card.TEN_SPADES, queen.getMove(cardsPlayed, hand, leadPlayer));

		
	}
	
	public void testGetMove2() {
		hand.add(Card.QUEEN_SPADES);
		hand.add(Card.TEN_DIAMONDS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_DIAMONDS);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.TEN_SPADES);
		
		cardsPlayed[0] = Card.FIVE_HEARTS;
		
		assertEquals(null, queen.getMove(cardsPlayed, hand, leadPlayer));

		
	}
	
	public void testGetMove3() {
		hand.add(Card.QUEEN_SPADES);
		hand.add(Card.TEN_DIAMONDS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.ACE_SPADES);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.TEN_SPADES);
		
		cardsPlayed[0] = Card.FIVE_SPADES;
	
		assertEquals(Card.ACE_SPADES, queen.getMove(cardsPlayed, hand, leadPlayer));
	
	}
	
	public void testGetMove4() {
		//hand.add(Card.QUEEN_SPADES);
		hand.add(Card.TEN_DIAMONDS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.ACE_SPADES);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.TEN_SPADES);
		
		cardsPlayed[0] = Card.FIVE_SPADES;
	
		assertEquals(Card.TEN_SPADES, queen.getMove(cardsPlayed, hand, leadPlayer));

	}
	
}
