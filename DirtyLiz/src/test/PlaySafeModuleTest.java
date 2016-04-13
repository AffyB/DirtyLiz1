package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

import ai.CardTracker;
import ai.PlaySafeModule;
import commmon.Card;
import commmon.MaxFourInt;

public class PlaySafeModuleTest {
	
	private PlaySafeModule ai;
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	MaxFourInt currentPlayer;
	CardTracker cardTracker;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		ai = new PlaySafeModule();
		cardsPlayed = new Card[3];
		suit = Card.CLUBS;
		leadPlayer = new MaxFourInt(0);
		currentPlayer = new MaxFourInt(2);
		cardTracker = cardTracker.getTracker();
	}

	@Test
	public void checkForSameSuitToPlayMethod() {
		
		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.KING_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS, Card.NINE_CLUBS};
		cardTracker.addToCards(Card.EIGHT_DIAMONDS, true);
		
		Card getMove = ai.getMove(playedCards, hand, leadPlayer);
		assertEquals(Card.EIGHT_CLUBS,getMove);
	}

	@Test
	public void checkPlayLowestCardMethod() {
		
		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.KING_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS, Card.NINE_CLUBS};
		cardTracker.addToCards(Card.EIGHT_DIAMONDS, true);
		
		Card getMove = ai.playLowestSameSuitCard(Card.CLUBS, hand);
		assertEquals(Card.TWO_CLUBS,getMove);
	}
	
	@Test
	public void testCheckForHeartMethod() {
		
		hand.add(Card.ACE_CLUBS);
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.KING_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS, Card.NINE_CLUBS};
		cardTracker.addToCards(Card.EIGHT_DIAMONDS, true);
		
		Card getMove = ai.checkForHeart(hand);
		assertEquals(Card.ACE_HEARTS,getMove);
	}
	
	@Test
	public void testPlayHighestValueCardIfNotLeadMethod() {
		
		hand.add(Card.TWO_CLUBS);
		hand.add(Card.ACE_HEARTS);
		hand.add(Card.EIGHT_CLUBS);
		hand.add(Card.FIVE_HEARTS);
		hand.add(Card.KING_HEARTS);
		
		Card[] playedCards = {Card.SEVEN_CLUBS, Card.NINE_CLUBS};
		cardTracker.addToCards(Card.EIGHT_DIAMONDS, true);
		
		Card getMove = ai.playHighestValueCardIfNotLead(hand);
		assertEquals(Card.ACE_HEARTS,getMove);
	}

}
