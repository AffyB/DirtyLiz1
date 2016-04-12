package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import ai.CardTracker;
import commmon.Card;

public class CardTrackerTest {

	ArrayList<Card> cardsPlayedTracker;
	private CardTracker tracking;
	Card[] cardsPlayed;
	List<Card> hand;
	
	@Before
	public void setUp() {
		cardsPlayedTracker = new ArrayList<Card>();
		tracking = CardTracker.getTracker();
		tracking.newGame();
		cardsPlayed = new Card[4];
		hand = new ArrayList<Card>();
	} 
	
	@Test
	public void testingTracker() {
		
		cardsPlayed[0] = Card.ACE_HEARTS;
		cardsPlayed[1] = Card.ACE_SPADES;
		cardsPlayed[2] = Card.KING_SPADES;
		cardsPlayed[3] = Card.JACK_CLUBS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i], true);
			cardsPlayedTracker = tracking.getPlayedCards();
		}
		
		int sizeOfArrayList = cardsPlayedTracker.size();
		
		assertEquals(4, sizeOfArrayList);
		
		cardsPlayed[0] = Card.FOUR_CLUBS;
		cardsPlayed[1] = Card.FOUR_DIAMONDS;
		cardsPlayed[2] = Card.SIX_DIAMONDS;
		cardsPlayed[3] = Card.SEVEN_DIAMONDS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i], true);
			cardsPlayedTracker = tracking.getPlayedCards();
		}
		
		sizeOfArrayList = cardsPlayedTracker.size();
		
		assertEquals(8, sizeOfArrayList);
		
		for(int i=0; i<sizeOfArrayList; i++){
			System.out.println(cardsPlayedTracker.get(i));
		}
	}
	
	@Test
	public void testNewGame() {
		tracking.newGame();
		cardsPlayed[0] = Card.FOUR_CLUBS;
		cardsPlayed[1] = Card.FOUR_DIAMONDS;
		cardsPlayed[2] = Card.SIX_DIAMONDS;
		cardsPlayed[3] = Card.SEVEN_DIAMONDS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i], true);
			System.out.println("THIS IS CARD HAS BEEN PLAYED " + cardsPlayed[i]);
		}
		cardsPlayedTracker = tracking.getPlayedCards();
		int sizeOfArrayList = cardsPlayedTracker.size();
		System.out.println("THIS IS THE SIZE OF THE ARRAY " + sizeOfArrayList);

		
		assertEquals(4, sizeOfArrayList);
		
		tracking.newGame();
		
		cardsPlayed[0] = Card.FOUR_CLUBS;
		cardsPlayed[1] = Card.FOUR_DIAMONDS;
		cardsPlayed[2] = Card.SIX_DIAMONDS;
		cardsPlayed[3] = Card.SEVEN_DIAMONDS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i], true);
			cardsPlayedTracker = tracking.getPlayedCards();
		}
		
		sizeOfArrayList = cardsPlayedTracker.size();
		
		assertEquals(4, sizeOfArrayList);
	}
	
	@Test
	public void testLeads() {
		tracking.newGame();
		tracking.addToCards(Card.ACE_CLUBS, true);
		tracking.addToCards(Card.TWO_CLUBS, true);
		assertEquals(2, tracking.numberOfTimesClubsIsLead());
		tracking.newGame();
		tracking.addToCards(Card.THREE_CLUBS, true);
		tracking.addToCards(Card.THREE_CLUBS, false);
		assertEquals(1, tracking.numberOfTimesClubsIsLead());
		assertEquals(1, tracking.numberOfTimesSuitIsLead(Card.CLUBS));
	}
	
	@Test
	public void testAvailableCards() {
		ArrayList<Card> clubs = tracking.findSuitableListForAvailableCards(Card.CLUBS);
		assertEquals(13, clubs.size());
		tracking.addToCards(Card.EIGHT_CLUBS, false);
		tracking.addToCards(Card.TEN_CLUBS, false);
		clubs = tracking.findSuitableListForAvailableCards(Card.CLUBS);
		assertEquals(11, clubs.size());
		tracking.newGame();
		clubs = tracking.findSuitableListForAvailableCards(Card.CLUBS);
		assertEquals(13, clubs.size());
	}
	

}
