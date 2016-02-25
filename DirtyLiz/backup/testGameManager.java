import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class testGameManager {

	private Card[] playedCards;
	private Player[] players;
	ArrayList<ArrayList<Card>> hands;
	private Deck deck;
	private GameManager gm;
	
	@Before
	public void setUp() {
		gm = new GameManager(1);
		Player Player1 = mock(Player.class, RETURNS_DEEP_STUBS);
		ArtificialIntelligence HighAi = mock(ArtificialIntelligence.class);
		when(Player1.getScore()).thenReturn(1);
		when(Player1.getAI()).thenReturn(HighAi);
	}

	@Test
	public void CalculateScoretest() {
		
		playedCards[0] = Card.ACE_DIAMONDS;
		playedCards[1] = Card.FIVE_DIAMONDS;
		playedCards[2] = Card.QUEEN_SPADES;
		playedCards[3] = Card.ACE_HEARTS;
		
		
		assertEquals("Expected: " + 18 + " Actual: " + gm.calculateScores(playedCards), 18, players.getScore());
	}

}
