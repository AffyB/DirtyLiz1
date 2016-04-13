package test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

import commmon.Card;

public class CardTest {

	@Test
	public void testSuits() {
		assertEquals(Card.HEARTS, Card.ACE_HEARTS.getSuit());
		assertEquals(Card.CLUBS, Card.THREE_CLUBS.getSuit());
		assertEquals(Card.SPADES, Card.SEVEN_SPADES.getSuit());
		assertEquals(Card.DIAMONDS, Card.ACE_DIAMONDS.getSuit());

		ArrayList<Card> fullDeck = Card.getDeck();
		assertEquals(13, findSuitTotal(Card.HEARTS, fullDeck));
		assertEquals(13, findSuitTotal(Card.CLUBS, fullDeck));
		assertEquals(13, findSuitTotal(Card.SPADES, fullDeck));
		assertEquals(13, findSuitTotal(Card.DIAMONDS, fullDeck));

	}

	@Test
	public void testValues() {
		assertEquals(4, findValueTotal(11, Card.getDeck()));
		assertEquals(4, findValueTotal(3, Card.getDeck()));
		assertEquals(4, findValueTotal(13, Card.getDeck()));
		assertEquals(13, Card.KING_CLUBS.getValue());
		assertEquals(14, Card.ACE_SPADES.getValue());
	}

	@Test
	public void testPoints() {
		assertEquals(5, Card.ACE_HEARTS.getPoints());
		assertEquals(1, Card.TEN_HEARTS.getPoints());
		assertEquals(0, Card.EIGHT_CLUBS.getPoints());
		ArrayList<Card> deck = Card.getDeck();
		int points = 0;
		for (int i = 0; i < deck.size(); i++) {
			points += deck.get(i).getPoints();
		}
		assertEquals(36, points);
	}

	@Test
	public void testDeckSize() {
		assertEquals(52, Card.getDeck().size());
	}

	private int findSuitTotal(char suit, ArrayList<Card> deck) {
		int total = 0;

		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getSuit() == suit) {
				total++;
			}
		}

		return total;
	}

	private int findValueTotal(int value, ArrayList<Card> deck) {
		int total = 0;

		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getValue() == value) {
				total++;
			}
		}

		return total;
	}
	

}
