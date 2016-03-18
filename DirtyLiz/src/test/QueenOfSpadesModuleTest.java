package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.QueenOfSpadesModule;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class QueenOfSpadesModuleTest {
	
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	private QueenOfSpadesModule queen;
	Player[] players;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		queen = new QueenOfSpadesModule();
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
	
//	@Test
//	public void testHighestHeart() {
//		hand.add(Card.TEN_HEARTS);
//		hand.add(Card.FIVE_HEARTS);
//		hand.add(Card.SIX_DIAMONDS);
//		hand.add(Card.EIGHT_DIAMONDS);
//		hand.add(Card.TWO_HEARTS);
//		hand.add(Card.NINE_SPADES);
//		hand.add(Card.ACE_HEARTS);
//		
//		cardsPlayed[0] = Card.JACK_HEARTS;
//		
//		assertEquals(Card.ACE_HEARTS, heart.findHighestHeart(hand));
//	}
//	
//	@Test
//	public void testLowesttHeart() {
//		hand.add(Card.TEN_HEARTS);
//		hand.add(Card.FIVE_HEARTS);
//		hand.add(Card.SIX_DIAMONDS);
//		hand.add(Card.EIGHT_DIAMONDS);
//		hand.add(Card.TWO_HEARTS);
//		hand.add(Card.NINE_SPADES);
//		hand.add(Card.ACE_HEARTS);
//		
//		cardsPlayed[0] = Card.JACK_HEARTS;
//		
//		assertEquals(Card.TWO_HEARTS, heart.findLowestHeart(hand));
//	}

	
}
