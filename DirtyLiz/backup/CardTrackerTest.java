import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CardTrackerTest {

	ArrayList<Card> cardsPlayedTracker;
	private CardTracker tracking;
	Card[] cardsPlayed;
	
	@Before
	public void setUp() {
		cardsPlayedTracker = new ArrayList<Card>();
		tracking = tracking.getTracker();
		cardsPlayed = new Card[4];
	}
	
	@Test
	public void testingTracker() {
		
		cardsPlayed[0] = Card.ACE_HEARTS;
		cardsPlayed[1] = Card.ACE_SPADES;
		cardsPlayed[2] = Card.KING_SPADES;
		cardsPlayed[3] = Card.JACK_CLUBS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i]);
			cardsPlayedTracker = tracking.getPlayedCards();
		}
		
		int sizeOfArrayList = cardsPlayedTracker.size();
		
		assertEquals(4, sizeOfArrayList);
		
		cardsPlayed[0] = Card.FOUR_CLUBS;
		cardsPlayed[1] = Card.FOUR_DIAMONDS;
		cardsPlayed[2] = Card.SIX_DIAMONDS;
		cardsPlayed[3] = Card.SEVEN_DIAMONDS;
		
		for(int i=0; i<cardsPlayed.length; i++){
			tracking.addToCards(cardsPlayed[i]);
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
			tracking.addToCards(cardsPlayed[i]);
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
			tracking.addToCards(cardsPlayed[i]);
			cardsPlayedTracker = tracking.getPlayedCards();
		}
		
		sizeOfArrayList = cardsPlayedTracker.size();
		
		assertEquals(4, sizeOfArrayList);
		
		
	}

}
