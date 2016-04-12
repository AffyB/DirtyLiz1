package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.CardTracker;
import ai.HeartsModule;
import ai.LeadModule;
import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class LeadModuleTest {
	
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	private HeartsModule heart;
	private LeadModule lead;
	Player[] players;
	private CardTracker tracker;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		heart = new HeartsModule();
		cardsPlayed = new Card[4];
		players = new Player[4];
		suit = Card.HEARTS;
		lead = new LeadModule();
		leadPlayer = new MaxFourInt(0);
		tracker = tracker.getTracker();
		lead.addTracker(tracker);
	}
	
	@Test
//	public void testGetMove(){
//		
//		hand.add(Card.SEVEN_CLUBS);
//		hand.add(Card.JACK_CLUBS);
//		hand.add(Card.ACE_HEARTS);
//		
//		tracker.addToCards(Card.FOUR_HEARTS, true);
//		tracker.addToCards(Card.THREE_HEARTS, false);
//		tracker.addToCards(Card.TWO_HEARTS, false);
//		tracker.addToCards(Card.SIX_HEARTS, false);
//		tracker.addToCards(Card.THREE_DIAMONDS, true);
//		tracker.addToCards(Card.SIX_DIAMONDS, false);
//		tracker.addToCards(Card.TWO_DIAMONDS, false);
//		tracker.addToCards(Card.FOUR_DIAMONDS, false);
//		tracker.addToCards(Card.SIX_CLUBS, true);
//		tracker.addToCards(Card.FIVE_CLUBS, false);
//		tracker.addToCards(Card.THREE_CLUBS, false);
//		tracker.addToCards(Card.FOUR_CLUBS, false);
//		tracker.addToCards(Card.SEVEN_SPADES, true);
//		tracker.addToCards(Card.FIVE_SPADES, false);
//		tracker.addToCards(Card.TWO_SPADES, false);
//		tracker.addToCards(Card.SIX_SPADES, false);
//		tracker.addToCards(Card.EIGHT_SPADES, true);
//		tracker.addToCards(Card.FOUR_SPADES, false);
//		tracker.addToCards(Card.KING_SPADES, false);
//		tracker.addToCards(Card.JACK_SPADES, false);
//		tracker.addToCards(Card.TWO_CLUBS, true);
//		tracker.addToCards(Card.EIGHT_CLUBS, false);
//		tracker.addToCards(Card.QUEEN_CLUBS, false);
//		tracker.addToCards(Card.KING_CLUBS, false);
//		tracker.addToCards(Card.SEVEN_DIAMONDS, true);
//		tracker.addToCards(Card.EIGHT_DIAMONDS, false);
//		tracker.addToCards(Card.FIVE_DIAMONDS, false);
//		tracker.addToCards(Card.TEN_DIAMONDS, false);
//		tracker.addToCards(Card.NINE_SPADES, true);
//		tracker.addToCards(Card.THREE_SPADES, false);
//		tracker.addToCards(Card.NINE_HEARTS, false);
//		tracker.addToCards(Card.ACE_SPADES, false);
//		tracker.addToCards(Card.EIGHT_HEARTS, true);
//		tracker.addToCards(Card.TEN_HEARTS, false);
//		tracker.addToCards(Card.SEVEN_HEARTS, false);
//		tracker.addToCards(Card.FIVE_HEARTS, false);
//		tracker.addToCards(Card.TEN_SPADES, true);
//		tracker.addToCards(Card.QUEEN_SPADES, false);
//		tracker.addToCards(Card.ACE_CLUBS, false);
//		tracker.addToCards(Card.QUEEN_HEARTS, false);
//		
//		assertEquals(Card.SEVEN_CLUBS, lead.getMove(cardsPlayed, hand, leadPlayer));
//		
//	}
	
	public void getMove(){
		hand.add(Card.KING_CLUBS);
		hand.add(Card.QUEEN_DIAMONDS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.QUEEN_HEARTS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_DIAMONDS);
		hand.add(Card.THREE_SPADES);
		hand.add(Card.KING_SPADES);
		hand.add(Card.TEN_CLUBS);
		hand.add(Card.JACK_HEARTS);
		
		
		tracker.addToCards(Card.TWO_DIAMONDS, true);
		tracker.addToCards(Card.FIVE_DIAMONDS, false);
		tracker.addToCards(Card.THREE_DIAMONDS, false);
		tracker.addToCards(Card.FOUR_DIAMONDS, false);
		tracker.addToCards(Card.TWO_HEARTS, true);
		tracker.addToCards(Card.FOUR_HEARTS, false);
		tracker.addToCards(Card.FIVE_HEARTS, false);
		tracker.addToCards(Card.SEVEN_HEARTS, false);
		tracker.addToCards(Card.EIGHT_HEARTS, true);
		tracker.addToCards(Card.THREE_HEARTS, false);
		tracker.addToCards(Card.NINE_HEARTS, false);
		tracker.addToCards(Card.SIX_HEARTS, false);
		
		assertEquals(Card.QUEEN_HEARTS, lead.getMove(cardsPlayed, hand, leadPlayer));

	}

}
