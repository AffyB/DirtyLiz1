package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.HeartsModule;
import ai.Smart_QueenFeature;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class HeartsModuleTest {
	
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	private HeartsModule heart;
	Player[] players;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		heart = new HeartsModule();
		cardsPlayed = new Card[3];
		players = new Player[3];
		suit = Card.HEARTS;
		leadPlayer = new MaxFourInt(0);
	}
	
	@Test
	public void testGetMove() {
		hand.add(Card.TEN_HEARTS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_HEARTS);
		hand.add(Card.NINE_SPADES);
		
		cardsPlayed[0] = Card.THREE_HEARTS;
		cardsPlayed[1] = Card.SEVEN_HEARTS;
	
		assertEquals(Card.FIVE_HEARTS, heart.getMove(cardsPlayed, hand, leadPlayer));
		
		cardsPlayed[0] = Card.JACK_HEARTS;
		
		assertEquals(Card.TEN_HEARTS, heart.getMove(cardsPlayed, hand, leadPlayer));
		
	}
	
	public void testGetMove2() {
		hand.add(Card.TEN_SPADES);
		hand.add(Card.FIVE_DIAMONDS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.NINE_SPADES);
		
		cardsPlayed[0] = Card.THREE_HEARTS;
		cardsPlayed[1] = Card.SEVEN_HEARTS;
	
		assertEquals(null, heart.getMove(cardsPlayed, hand, leadPlayer));

		
	}
	
	public void testGetMove3() {
		hand.add(Card.TEN_HEARTS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_HEARTS);
		hand.add(Card.NINE_SPADES);
	
		assertEquals(null, heart.getMove(cardsPlayed, hand, leadPlayer));
	
	}
	
	public void testGetMove4() {
		hand.add(Card.TEN_HEARTS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_HEARTS);
		hand.add(Card.NINE_SPADES);
		
		cardsPlayed[0] = Card.THREE_SPADES;
	
		assertEquals(null, heart.getMove(cardsPlayed, hand, leadPlayer));

	}
	
	@Test
	public void testHighestHeart() {
		hand.add(Card.TEN_HEARTS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_HEARTS);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.ACE_HEARTS);
		
		cardsPlayed[0] = Card.JACK_HEARTS;
		
		assertEquals(Card.ACE_HEARTS, heart.findHighestHeart(hand));
	}
	
	@Test
	public void testLowesttHeart() {
		hand.add(Card.TEN_HEARTS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.SIX_DIAMONDS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.TWO_HEARTS);
		hand.add(Card.NINE_SPADES);
		hand.add(Card.ACE_HEARTS);
		
		cardsPlayed[0] = Card.JACK_HEARTS;
		
		assertEquals(Card.TWO_HEARTS, heart.findLowestHeart(hand));
	}

	
}
