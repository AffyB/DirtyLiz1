package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.PlaySafeModule;
import commmon.Card;
import commmon.MaxFourInt;

public class PlaySafeModule_test {
	
	private PlaySafeModule ai;
	Card[] cardsPlayed;
	private List<Card> hand;
	char suit;
	MaxFourInt leadPlayer;
	
	@Before
	public void setUp() {
		hand = new ArrayList<Card>();
		ai = new PlaySafeModule();
		cardsPlayed = new Card[3];
		suit = Card.CLUBS;
		leadPlayer = new MaxFourInt(0);
	}

	@Test
	public void checkForSameSuitToPlayMethod() {
		
	}



}
