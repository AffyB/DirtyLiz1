//package test;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import ai.CardTracker;
//import ai.PlayHighModule;
//import ai.PlaySafeModule;
//import ai.Smart_SpadeFeature;
//import commmon.Card;
//import commmon.MaxFourInt;
//import gamelogic.Player;
//
//public class QueenFeatureAITest {
//
//	Card[] cardsPlayed;
//	private List<Card> hand;
//	char suit;
//	MaxFourInt leadPlayer;
//	private Smart_SpadeFeature queen;
//	Player[] players;
//	
//	@Before
//	public void setUp() {
//		hand = new ArrayList<Card>();
//		queen = new Smart_SpadeFeature(null);
//		cardsPlayed = new Card[3];
//		players = new Player[3];
//		suit = Card.CLUBS;
//		leadPlayer = new MaxFourInt(0);
//	}
//	
//	@Test
//	public void testGetMove() {
//		hand.add(Card.QUEEN_SPADES);
//		hand.add(Card.TEN_DIAMONDS);
//		hand.add(Card.SIX_DIAMONDS);
//		hand.add(Card.EIGHT_DIAMONDS);
//		hand.add(Card.TWO_DIAMONDS);
//		hand.add(Card.NINE_SPADES);
//		
//		cardsPlayed[0] = Card.FIVE_DIAMONDS;
//		cardsPlayed[1] = Card.SEVEN_DIAMONDS;
//	
//		assertEquals(Card.TEN_DIAMONDS, queen.getMove(cardsPlayed, hand, players, leadPlayer));
//	}
//
//}
