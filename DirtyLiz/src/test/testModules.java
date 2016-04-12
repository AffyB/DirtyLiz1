package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.CardTracker;
import ai.HeartsModule;
import ai.Smart_HeartFeature;
import ai.Smart_SpadeFeature;
import commmon.Card;
import commmon.Deck;
import commmon.MaxFourInt;
import gamelogic.Player;

public class testModules {
	
	Card[] cardsPlayed;
	char suit;
	MaxFourInt leadPlayer;
	Player[] players;
	private Deck deck;
	private Card[] playedCards;
	ArrayList<ArrayList<Card>> hands;
	private CardTracker trackCards;
	private Player player2;
	private Player player1;
	private Card card1;
	private Card card2;


	@Before
	public void setUp() {
		deck = Deck.getDeck();
		playedCards = new Card[4];
		
		players = new Player[3];
		leadPlayer = new MaxFourInt(0);
		trackCards = CardTracker.getTracker();
		player1 = new Player(new Smart_SpadeFeature(trackCards));
		player2 = new Player(new Smart_HeartFeature(trackCards));
		players[0] = player1;
		players[1] = player2;
		dealCards();
		
	}

	private void dealCards() {
		deck.shuffle();
		hands = deck.deal();
		player1.giveCards(hands.get(0));

	}
	
	@Test
	public void test(){
		
		playedCards[0] = hands.get(1).get(3);
		playedCards[1] = hands.get(1).get(8);
		
		
		card1 = player1.promptMove(playedCards, players, leadPlayer);
		hands.get(0).add(card1);
		players[1].giveCards(hands.get(0));
		card2 = player2.promptMove(playedCards, players, leadPlayer);
		
		System.out.println("PlayedCard 1 is " + playedCards[0] + " ||||| PlayedCard 2 is " + playedCards[1]);
		
		if(card1 != card2) {
			System.out.println("Card 1 is " + card1 + " ||||| Card 2 is " + card2);
		}
		
		System.out.println("Card 1 is " + card1 + " ||||| Card 2 is " + card2);

	}
	

}
